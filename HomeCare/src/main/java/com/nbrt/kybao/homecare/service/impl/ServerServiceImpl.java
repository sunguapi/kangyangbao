package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.entity.*;
import com.nbrt.kybao.homecare.service.*;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import com.nbrt.kybao.homecare.vo.ServerItemSaveVo;
import com.nbrt.kybao.homecare.vo.ServerListVo;
import com.nbrt.kybao.homecare.vo.ServerVo;
import com.nbrt.kybao.homecare.vo.server.ServerChangeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.homecare.dao.ServerDao;
import org.springframework.util.StringUtils;


@Service("serverService")
public class ServerServiceImpl extends ServiceImpl<ServerDao, ServerEntity> implements ServerService {

    private final CatItemService catItemService;

    private final ServerDao serverDao;

    private final ServerItemService serverItemService;

    private final ServerServeritemRelationService serverServeritemRelationService;

    private final ServerCatitemRelationService serverCatitemRelationService;

    private final ServerItemImagesService serverItemImagesService;

    public ServerServiceImpl(CatItemService catItemService, ServerDao serverDao, ServerItemService serverItemService, ServerServeritemRelationService serverServeritemRelationService, ServerCatitemRelationService serverCatitemRelationService, ServerItemImagesService serverItemImagesService) {
        this.catItemService = catItemService;
        this.serverDao = serverDao;
        this.serverItemService = serverItemService;
        this.serverServeritemRelationService = serverServeritemRelationService;
        this.serverCatitemRelationService = serverCatitemRelationService;
        this.serverItemImagesService = serverItemImagesService;
    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<ServerEntity> queryWrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)) {
            queryWrapper.like("server_name",key);
        }

        Long ServerId = (Long) params.get("serverId");
        if(!StringUtils.isEmpty(ServerId)) {
            queryWrapper.eq("server_id",key);
        }

        IPage<ServerEntity> page = this.page(
                new Query<ServerEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    /**
     * 查询服务列表页面数据
     * @param catItemId 分类项id
     * @return 返回服务列表封装类数据
     */
    @Override
    public ServerVo getServerData(Long catItemId) {
        ServerVo serverVo = new ServerVo();
        //1.根据分类项id查出分类项名称
        CatItemEntity catItemEntity = catItemService.getById(catItemId);
        if(catItemEntity != null) {
            serverVo.setCategoryName(catItemEntity.getCatItemName());
        }
        //2.根据分类项id查询服务名称与服务图标，以及每个服务的id.
        QueryWrapper<ServerCatitemRelationEntity> catServerWrapper = new QueryWrapper<>();
        catServerWrapper.eq("cat_item_id",catItemId);
        List<ServerCatitemRelationEntity> serverCatitemRelationEntities  = serverCatitemRelationService.getBaseMapper().selectList(catServerWrapper);
        List<Long> serverIds;
        List<ServerEntity> serverEntities = null;
        if(serverCatitemRelationEntities != null && serverCatitemRelationEntities.size() > 0) {
            serverIds = serverCatitemRelationEntities.stream().map(ServerCatitemRelationEntity::getServerId).collect(Collectors.toList());
            serverEntities = serverDao.selectBatchIds(serverIds);
        }

        if(serverEntities != null && serverEntities.size() > 0) {
            List<ServerListVo> serverListVos = serverEntities.stream().map(item -> {
                ServerListVo serverListVo = new ServerListVo();
                if(item != null) {
                    serverListVo.setServerId(item.getServerId());
                    serverListVo.setServerName(item.getServerName());
                    serverListVo.setIcon(item.getIcon());
                    if(item.getDefaultOn() == 1) {
                        serverVo.setDefaultOn(item.getServerId());
                    }
                }
                return serverListVo;
            }).collect(Collectors.toList());

            serverVo.setServerListVos(serverListVos);
            //3.如果为默认选中，则查出当前服务下的所有服务项。
            Long defaultOn = serverVo.getDefaultOn();
            QueryWrapper<ServerServeritemRelationEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("server_id",defaultOn);
            List<ServerServeritemRelationEntity> serverServeritemRelationEntities = serverServeritemRelationService.getBaseMapper().selectList(wrapper);
            if(serverServeritemRelationEntities.size() > 0) {
                List<Long> serverItemIds = serverServeritemRelationEntities.stream().map(ServerServeritemRelationEntity::getServerItemId).collect(Collectors.toList());
                List<ServerItemSaveVo> serverItemVos = serverItemIds.stream().map(item -> {
                    ServerItemSaveVo serverItemVo = new ServerItemSaveVo();
                    QueryWrapper<ServerItemImagesEntity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("server_item_id", item);
                    List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(queryWrapper);
                    if(serverItemImagesEntities != null && serverItemImagesEntities.size() > 0) {
                        for (ServerItemImagesEntity serverItemImagesEntity : serverItemImagesEntities) {
                            if (serverItemImagesEntity != null && serverItemImagesEntity.getDefaultImg() == 1) {
                                serverItemVo.setDefaultImage(serverItemImagesEntity.getImgUrl());
                            }
                        }
                    }
                    ServerItemEntity itemEntity = serverItemService.getById(item);
                    BeanUtils.copyProperties(itemEntity,serverItemVo);
                    return serverItemVo;
                }).collect(Collectors.toList());

                serverVo.setServerItemVos(serverItemVos);
            }
        }
        return serverVo;
    }

    /**
     * 切换服务
     * @param serverId 切换的服务id
     * @return 返回切换后的服务下的所有服务项信息
     */
    @Override
    public ServerChangeVo changeServer(Long serverId, Long catItemId) {
        ServerChangeVo serverChangeVo = new ServerChangeVo();
        //设置服务项信息
        QueryWrapper<ServerServeritemRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("server_id",serverId);
        List<ServerServeritemRelationEntity> serverServeritemRelationEntities = serverServeritemRelationService.list(wrapper);
        List<ServerItemSaveVo> serverItemVos = null;
        if(serverServeritemRelationEntities != null && serverServeritemRelationEntities.size() > 0) {
            List<Long> serverItemIds = serverServeritemRelationEntities.stream().map(ServerServeritemRelationEntity::getServerItemId).collect(Collectors.toList());
            serverItemVos = serverItemIds.stream().map(item -> {
                ServerItemSaveVo serverItemVo = new ServerItemSaveVo();
                QueryWrapper<ServerItemImagesEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("server_item_id", item);
                List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(queryWrapper);
                for (ServerItemImagesEntity serverItemImagesEntity : serverItemImagesEntities) {
                    if (serverItemImagesEntity.getDefaultImg() == 1) {
                        serverItemVo.setDefaultImage(serverItemImagesEntity.getImgUrl());
                    }
                }
                ServerItemEntity itemEntity = serverItemService.getById(item);

                BeanUtils.copyProperties(itemEntity,serverItemVo);

                return serverItemVo;
            }).collect(Collectors.toList());
        }
        serverChangeVo.setServerItemSaveVos(serverItemVos);

        //设置服务信息
        QueryWrapper<ServerCatitemRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cat_item_id",catItemId);
        List<ServerCatitemRelationEntity> catitemRelationEntities = serverCatitemRelationService.list(queryWrapper);
        List<Long> ids = catitemRelationEntities.stream().map(ServerCatitemRelationEntity::getServerId).collect(Collectors.toList());
        List<ServerEntity> serverEntities = this.listByIds(ids);
        List<ServerListVo> serverListVos = serverEntities.stream().map(item -> {
            ServerListVo serverListVo = new ServerListVo();
            serverListVo.setServerId(item.getServerId());
            serverListVo.setServerName(item.getServerName());
            return serverListVo;
        }).collect(Collectors.toList());
        serverChangeVo.setServerListVos(serverListVos);
        return serverChangeVo;
    }

    /**
     * 排序切换
     * @param serverId 服务项id
     * @param orderNum 排序数字
     * @return 返回排序好的服务项数据
     */
    @Override
    public List<ServerItemSaveVo> orderChange(Long serverId, Long orderNum) {
        Comparator<ServerItemSaveVo> comparator = (o1, o2) -> {
            //好评率降序排序
            if (orderNum == 1) {
                return o2.getPraiseRate() - o1.getPraiseRate();
                //价格升序排序
            } else if (orderNum == 2) {
                return new BigDecimal(o1.getPrice().toString()).
                        compareTo(new BigDecimal(o2.getPrice().toString()));
                //销量降序排序
            } else if (orderNum == 3) {
                return (o2.getSaleCount().intValue() - o1.getSaleCount().intValue());
                //到达时间升序排序
            } else if (orderNum == 4) {
                String[] s1 = o1.getArrivalTime().split("最快");
                String[] s2 = o2.getArrivalTime().split("最快");
                System.out.println("s1"+s1.length + s1[1]);
                return Integer.parseInt(s1[1].charAt(0) + "") - Integer.parseInt(s2[1].charAt(0) + "");
            } else {
                //TODO 推荐排序
                return new BigDecimal(o1.getPrice().toString()).
                        compareTo(new BigDecimal(o2.getPrice().toString()));
            }
        };
        QueryWrapper<ServerServeritemRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("server_id",serverId);
        List<ServerServeritemRelationEntity> serverServeritemRelationEntities = serverServeritemRelationService.list(wrapper);
        List<Long> serverItemIds = serverServeritemRelationEntities.stream().map(ServerServeritemRelationEntity::getServerItemId).collect(Collectors.toList());
        List<ServerItemSaveVo> serverItemVos = serverItemIds.stream().map(item -> {
            ServerItemSaveVo serverItemVo = new ServerItemSaveVo();
            QueryWrapper<ServerItemImagesEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("server_item_id", item);
            List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(queryWrapper);
            for (ServerItemImagesEntity serverItemImagesEntity : serverItemImagesEntities) {
                if (serverItemImagesEntity.getDefaultImg() == 1) {
                    serverItemVo.setDefaultImage(serverItemImagesEntity.getImgUrl());
                }
            }
            ServerItemEntity itemEntity = serverItemService.getById(item);
            BeanUtils.copyProperties(itemEntity,serverItemVo);
            return serverItemVo;
        }).collect(Collectors.toList());
        serverItemVos.sort(comparator);
        return serverItemVos;
    }



}