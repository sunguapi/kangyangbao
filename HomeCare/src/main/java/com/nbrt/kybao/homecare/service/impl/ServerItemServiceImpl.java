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
     * ??????????????????????????????
     * @param params ?????????????????????
     * @return ???????????????
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<ServerItemEntity> queryWrapper = new QueryWrapper<>();

        List<Long> ids = null;

        //??????serverId
        Object serverIdObj = params.get("serverId");

        //??????????????????????????????????????????????????????id
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

        //????????????id???ids??????????????????
        if(ids != null && ids.size() > 0) {
            queryWrapper.in("server_item_id",ids);
        }

        //??????key????????????
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key) && !key.equals("")) {
            queryWrapper.like("server_item_name",key);
        }

        //???????????????????????????????????????
        IPage<ServerItemEntity> page = this.page(
                new Query<ServerItemEntity>().getPage(params),
                queryWrapper
        );

        //???????????????????????????????????????
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
        //?????????????????????
        page.setRecords(collect);

        return new PageUtils(page);
    }

    /**
     * ???????????????????????????
     * @param serverItemId ?????????id
     * @return ?????????????????????????????????
     */
    @Override
    public ServerItemDetailVo getServerItemDetail(Long serverItemId) {

        ServerItemDetailVo serverItemDetailVo = new ServerItemDetailVo();
        //1.??????????????????????????????
        ServerItemEntity serverItemEntity = this.baseMapper.selectById(serverItemId);
        serverItemDetailVo.setServerItemId(serverItemEntity.getServerItemId());
        serverItemDetailVo.setServerItemName(serverItemEntity.getServerItemName());
        serverItemDetailVo.setDescript(serverItemEntity.getDescript());
        serverItemDetailVo.setPrice(serverItemEntity.getPrice());
        serverItemDetailVo.setSaleCount(serverItemEntity.getSaleCount());
        serverItemDetailVo.setAppointmentTime(serverItemEntity.getArrivalTime());
        serverItemDetailVo.setDgxz(serverItemEntity.getDgxz());
        serverItemDetailVo.setExplain(serverItemEntity.getExplainServerItem());
        //2.????????????????????????????????????
        QueryWrapper<ServerItemImagesEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("server_item_id", serverItemId);
        List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(wrapper);
        List<String> images = serverItemImagesEntities.stream().map(ServerItemImagesEntity::getImgUrl).collect(Collectors.toList());
        serverItemDetailVo.setImages(images);
        //3.TODO ??????????????????????????????
        return serverItemDetailVo;
    }

    /**
     * ?????????????????????
     * @param serverItemVo ?????????????????????
     */
    @Override
    public void saveSeverItem(com.nbrt.kybao.homecare.vo.severItem.ServerItemSaveVo serverItemVo) {

        //???????????????????????????
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
        //????????????????????????
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
        //????????????????????????????????????
        ServerServeritemRelationEntity serverServeritemRelationEntity = new ServerServeritemRelationEntity();
        serverServeritemRelationEntity.setServerId(serverItemVo.getServerId());
        serverServeritemRelationEntity.setServerItemId(serverItemEntity.getServerItemId());
        serverServeritemRelationService.save(serverServeritemRelationEntity);
    }

    /**
     * ???????????????
     * @param serverItemId ?????????Id
     */
    @Override
    public void removeServerItemById(Long serverItemId) {
        //????????????????????????????????????
        QueryWrapper<ServerServeritemRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("server_item_id",serverItemId);
        ServerServeritemRelationEntity serverServeritemRelationEntity = serverServeritemRelationService.getOne(wrapper);
        serverServeritemRelationService.removeById(serverServeritemRelationEntity.getId());
        //????????????????????????
        QueryWrapper<ServerItemImagesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_item_id",serverItemId);
        List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(queryWrapper);
        List<Long> ids = serverItemImagesEntities.stream().map(ServerItemImagesEntity::getId).collect(Collectors.toList());
        serverItemImagesService.removeByIds(ids);
        //???????????????????????????
        this.removeById(serverItemId);
    }


    /**
     * ??????????????????????????????
     * @return ????????????
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
     * ????????????
     * @param serverItem ?????????????????????
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
     * ?????????????????????
     * @param serverItemId ?????????id
     * @return ???????????????vo
     */
    @Override
    public ServerItemUpdateVo getServerItemById(Long serverItemId) {
        ServerItemUpdateVo serverItemUpdateVo = new ServerItemUpdateVo();

        //?????????????????????
        ServerItemEntity serverItemEntity = this.getById(serverItemId);
        serverItemUpdateVo.setServerItemId(serverItemEntity.getServerItemId());
        serverItemUpdateVo.setServerTime(serverItemEntity.getServerTime());
        serverItemUpdateVo.setDescript(serverItemEntity.getDescript());
        serverItemUpdateVo.setServerPrice(serverItemEntity.getPrice());
        serverItemUpdateVo.setDgxz(serverItemEntity.getDgxz());
        serverItemUpdateVo.setServerItemName(serverItemEntity.getServerItemName());

        //??????id
        Long[] parentPath = this.getParentPath(serverItemId);
        serverItemUpdateVo.setIds(parentPath);

        //??????????????????
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