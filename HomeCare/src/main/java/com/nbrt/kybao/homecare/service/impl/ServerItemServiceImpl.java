package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.dao.CategoryCatitemRelationDao;
import com.nbrt.kybao.homecare.dao.ServerDao;
import com.nbrt.kybao.homecare.entity.*;
import com.nbrt.kybao.homecare.service.*;
import com.nbrt.kybao.homecare.utils.MyPageUtils;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;

import com.nbrt.kybao.homecare.vo.ServerItemDetailVo;


import com.nbrt.kybao.homecare.vo.severItem.ServerItemUpdateVo;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.nbrt.kybao.homecare.dao.ServerItemDao;
import org.springframework.util.StringUtils;


@Service("serverItemService")
public class ServerItemServiceImpl extends ServiceImpl<ServerItemDao, ServerItemEntity> implements ServerItemService {

    private final ServerItemImagesService serverItemImagesService;
    private final ServerDao serverDao;
    private final ServerServeritemRelationService serverServeritemRelationService;
    private final ServerCatitemRelationService serverCatitemRelationService;
    private final CategoryCatitemRelationDao categoryCatitemRelationDao;

    public ServerItemServiceImpl(ServerItemImagesService serverItemImagesService, ServerDao serverDao, ServerServeritemRelationService serverServeritemRelationService, ServerCatitemRelationService serverCatitemRelationService, CategoryCatitemRelationDao categoryCatitemRelationDao) {
        this.serverItemImagesService = serverItemImagesService;
        this.serverDao = serverDao;
        this.serverServeritemRelationService = serverServeritemRelationService;
        this.serverCatitemRelationService = serverCatitemRelationService;
        this.categoryCatitemRelationDao = categoryCatitemRelationDao;
    }

    /**
     * 条件带分页查询服务项
     * @param params 前台条件键值对
     * @return 服务项列表
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<ServerItemEntity> queryWrapper = new QueryWrapper<>();

        List<Long> ids = null;

        //获取serverId
        Object serverIdObj = params.get("serverId");

        //不为空则查出该服务下关联的所有服务项id
        if(serverIdObj != null && !serverIdObj.toString().equals("")) {
            Long serverId = Long.parseLong(params.get("serverId").toString());
            QueryWrapper<ServerServeritemRelationEntity> objectQueryWrapper = new QueryWrapper<>();
            objectQueryWrapper.eq("server_id",serverId);
            List<ServerServeritemRelationEntity> serverServeritemRelationEntities = serverServeritemRelationService.list(objectQueryWrapper);
            ids = serverServeritemRelationEntities.stream().map(ServerServeritemRelationEntity::getServerItemId).collect(Collectors.toList());
        }

        if(ids != null && ids.size() == 0) {
            IPage<ServerItemEntity> page = this.page(
                    new Query<ServerItemEntity>().getPage(params),
                    queryWrapper
            );
            PageUtils pageUtils = new PageUtils(page);
            pageUtils.setTotalCount(0);
            pageUtils.setCurrPage(1);
            pageUtils.setTotalPage(0);
            pageUtils.setList(new ArrayList<>());
            pageUtils.setPageSize(10);
            return pageUtils;
        }

        //取服务项id在ids里面的服务项
        if(ids != null && ids.size() > 0) {
            queryWrapper.in("server_item_id",ids);
        }

        //拼接key模糊查询
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key) && !key.equals("")) {
            queryWrapper.like("server_item_name",key);
        }

        //查询出所有符合条件的服务项
        IPage<ServerItemEntity> page = this.page(
                new Query<ServerItemEntity>().getPage(params),
                queryWrapper
        );

        //设置服务项所关联的服务名称
        List<ServerItemEntity> records = page.getRecords();
        List<ServerItemEntity> collect = records.stream().map(item -> {
            QueryWrapper<ServerServeritemRelationEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("server_item_id", item.getServerItemId());
            ServerServeritemRelationEntity serverServeritemRelationServiceOne = serverServeritemRelationService.getOne(wrapper);
            if(serverServeritemRelationServiceOne != null) {
                ServerEntity serverEntity = serverDao.selectById(serverServeritemRelationServiceOne.getServerId());
                if(serverEntity != null) {
                    item.setServerType(serverEntity.getServerName());
                }
            }
            return item;
        }).collect(Collectors.toList());
        //重新设置记录集
        page.setRecords(collect);

        return new PageUtils(page);
    }

    /**
     * 获取服务项详情数据
     * @param serverItemId 服务项id
     * @return 返回服务项详情页面数据
     */
    @Override
    public ServerItemDetailVo getServerItemDetail(Long serverItemId) {

        ServerItemDetailVo serverItemDetailVo = new ServerItemDetailVo();
        //1.查询出服务项相关信息
        ServerItemEntity serverItemEntity = this.baseMapper.selectById(serverItemId);
        serverItemDetailVo.setServerItemId(serverItemEntity.getServerItemId());
        serverItemDetailVo.setServerItemName(serverItemEntity.getServerItemName());
        serverItemDetailVo.setDescript(serverItemEntity.getDescript());
        serverItemDetailVo.setPrice(serverItemEntity.getPrice());
        serverItemDetailVo.setSaleCount(serverItemEntity.getSaleCount());
        serverItemDetailVo.setAppointmentTime(serverItemEntity.getArrivalTime());
        serverItemDetailVo.setDgxz(serverItemEntity.getDgxz());
        serverItemDetailVo.setExplain(serverItemEntity.getExplainServerItem());
        //2.查询本服务项的所有图片集
        QueryWrapper<ServerItemImagesEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("server_item_id", serverItemId);
        List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(wrapper);
        List<String> images = serverItemImagesEntities.stream().map(ServerItemImagesEntity::getImgUrl).collect(Collectors.toList());
        serverItemDetailVo.setImages(images);
        //3.TODO 查询本服务项评论列表
        return serverItemDetailVo;
    }

    /**
     * 保存服务项信息
     * @param serverItemVo 封装服务项信息
     */
    @Override
    public void saveSeverItem(com.nbrt.kybao.homecare.vo.severItem.ServerItemSaveVo serverItemVo) {

        //保存服务项基本信息
        ServerItemEntity serverItemEntity = new ServerItemEntity();
        serverItemEntity.setServerItemName(serverItemVo.getServerItemName());
        serverItemEntity.setDescript(serverItemVo.getDescript());
        serverItemEntity.setPrice(serverItemVo.getServerPrice());
        serverItemEntity.setServerTime(serverItemVo.getServerTime());
        String dgxz = serverItemVo.getDgxz();
        serverItemEntity.setDgxz(dgxz);
        serverItemEntity.setCreateTime(new Date());
        serverItemEntity.setUpdateTime(new Date());
        this.save(serverItemEntity);
        //保存服务项图片集
        boolean flag = false;
        for (String image : serverItemVo.getImages()) {
            ServerItemImagesEntity serverItemImagesEntity = new ServerItemImagesEntity();
            serverItemImagesEntity.setServerItemId(serverItemEntity.getServerItemId());
            serverItemImagesEntity.setImgUrl(image);
            if(!flag) {
                serverItemImagesEntity.setDefaultImg(1);
                flag = true;
            }
            serverItemImagesService.save(serverItemImagesEntity);
        }
        //保存服务项和服务关联信息
        ServerServeritemRelationEntity serverServeritemRelationEntity = new ServerServeritemRelationEntity();
        serverServeritemRelationEntity.setServerId(serverItemVo.getServerId());
        serverServeritemRelationEntity.setServerItemId(serverItemEntity.getServerItemId());
        serverServeritemRelationService.save(serverServeritemRelationEntity);
    }

    /**
     * 删除服务项
     * @param serverItemId 服务项Id
     */
    @Override
    public void removeServerItemById(Long serverItemId) {
        //删除服务项与服务关联信息
        QueryWrapper<ServerServeritemRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("server_item_id",serverItemId);
        ServerServeritemRelationEntity serverServeritemRelationEntity = serverServeritemRelationService.getOne(wrapper);
        serverServeritemRelationService.removeById(serverServeritemRelationEntity.getId());
        //删除服务项图片集
        QueryWrapper<ServerItemImagesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_item_id",serverItemId);
        List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(queryWrapper);
        List<Long> ids = serverItemImagesEntities.stream().map(ServerItemImagesEntity::getId).collect(Collectors.toList());
        serverItemImagesService.removeByIds(ids);
        //删除服务项基本信息
        this.removeById(serverItemId);
    }


    /**
     * 获取该服务项的父路径
     * @return 返回路径
     */
    private Long[] getParentPath(Long serverItemId) {
        List<Long> list = new ArrayList<>();
        QueryWrapper<ServerServeritemRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("server_item_id",serverItemId);
        ServerServeritemRelationEntity serverServeritemRelationEntity = serverServeritemRelationService.getOne(wrapper);
        if(serverServeritemRelationEntity != null) {
            list.add(serverServeritemRelationEntity.getServerId());
            QueryWrapper<ServerCatitemRelationEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("server_id",serverServeritemRelationEntity.getServerId());
            ServerCatitemRelationEntity serverCatitemRelationEntity = serverCatitemRelationService.getOne(queryWrapper);
            if(serverCatitemRelationEntity != null) {
                list.add(serverCatitemRelationEntity.getCatItemId());
                QueryWrapper<CategoryCatitemRelationEntity> categoryCatitemRelationEntityQueryWrapper = new QueryWrapper<>();
                categoryCatitemRelationEntityQueryWrapper.eq("cat_item_id",serverCatitemRelationEntity.getCatItemId());
                CategoryCatitemRelationEntity categoryCatitemRelationEntity = categoryCatitemRelationDao.selectOne(categoryCatitemRelationEntityQueryWrapper);
                list.add(categoryCatitemRelationEntity.getCategoryId());
            }
        }
        Collections.reverse(list);
        return list.toArray(new Long[list.size()]);
    }

    /**
     * 修改分类
     * @param serverItem 服务项封装数据
     */
    @Override
    public void updateServerItemById(com.nbrt.kybao.homecare.vo.severItem.ServerItemSaveVo serverItem) {

        System.out.println(serverItem + "serverItem====>");

        ServerItemEntity serverItemEntity = new ServerItemEntity();
        serverItemEntity.setServerItemId(serverItem.getServerItemId());
        serverItemEntity.setServerItemName(serverItem.getServerItemName());
        serverItemEntity.setPrice(serverItem.getServerPrice());
        serverItemEntity.setServerTime(serverItem.getServerTime());
        serverItemEntity.setDescript(serverItem.getDescript());
        serverItemEntity.setDgxz(serverItem.getDgxz());
        serverItemEntity.setUpdateTime(new Date());
        this.updateById(serverItemEntity);

        QueryWrapper<ServerItemImagesEntity> serverItemImagesEntityQueryWrapper = new QueryWrapper<>();
        serverItemImagesEntityQueryWrapper.eq("server_item_id",serverItem.getServerItemId());
        List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(serverItemImagesEntityQueryWrapper);
        List<Long> ids = serverItemImagesEntities.stream().map(ServerItemImagesEntity::getId).collect(Collectors.toList());
        serverItemImagesService.removeByIds(ids);
        for (String image : serverItem.getImages()) {
            ServerItemImagesEntity serverItemImagesEntity = new ServerItemImagesEntity();
            serverItemImagesEntity.setServerItemId(serverItem.getServerItemId());
            serverItemImagesEntity.setImgUrl(image);
            serverItemImagesService.save(serverItemImagesEntity);
        }

        QueryWrapper<ServerServeritemRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_item_id",serverItem.getServerItemId());
        ServerServeritemRelationEntity relationEntity = serverServeritemRelationService.getOne(queryWrapper);
        serverServeritemRelationService.removeById(relationEntity.getId());

        ServerServeritemRelationEntity serverServeritemRelationEntity = new ServerServeritemRelationEntity();
        serverServeritemRelationEntity.setServerId(serverItem.getServerId());
        serverServeritemRelationEntity.setServerItemId(serverItem.getServerItemId());
        serverServeritemRelationService.save(serverServeritemRelationEntity);

    }

    /**
     * 查询服务项信息
     * @param serverItemId 服务项id
     * @return 服务项信息vo
     */
    @Override
    public ServerItemUpdateVo getServerItemById(Long serverItemId) {
        ServerItemUpdateVo serverItemUpdateVo = new ServerItemUpdateVo();

        //服务项基本信息
        ServerItemEntity serverItemEntity = this.getById(serverItemId);
        serverItemUpdateVo.setServerItemId(serverItemEntity.getServerItemId());
        serverItemUpdateVo.setServerTime(serverItemEntity.getServerTime());
        serverItemUpdateVo.setDescript(serverItemEntity.getDescript());
        serverItemUpdateVo.setServerPrice(serverItemEntity.getPrice());
        serverItemUpdateVo.setDgxz(serverItemEntity.getDgxz());
        serverItemUpdateVo.setServerItemName(serverItemEntity.getServerItemName());

        //服务id
        Long[] parentPath = this.getParentPath(serverItemId);
        serverItemUpdateVo.setIds(parentPath);

        //服务项图片集
        QueryWrapper<ServerItemImagesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_item_id",serverItemEntity.getServerItemId());
        List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(queryWrapper);
        if(serverItemImagesEntities != null) {
            List<String> images = serverItemImagesEntities.stream().map(ServerItemImagesEntity::getImgUrl).collect(Collectors.toList());
            serverItemUpdateVo.setImages(images);
        }

        return serverItemUpdateVo;
    }

}