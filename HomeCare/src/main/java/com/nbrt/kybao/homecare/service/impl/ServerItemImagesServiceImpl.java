package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.homecare.dao.ServerItemImagesDao;
import com.nbrt.kybao.homecare.entity.ServerItemImagesEntity;
import com.nbrt.kybao.homecare.service.ServerItemImagesService;


@Service("serverItemImagesService")
public class ServerItemImagesServiceImpl extends ServiceImpl<ServerItemImagesDao, ServerItemImagesEntity> implements ServerItemImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ServerItemImagesEntity> page = this.page(
                new Query<ServerItemImagesEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}