package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.homecare.dao.ServerCatitemRelationDao;
import com.nbrt.kybao.homecare.entity.ServerCatitemRelationEntity;
import com.nbrt.kybao.homecare.service.ServerCatitemRelationService;


@Service("serverCatitemRelationService")
public class ServerCatitemRelationServiceImpl extends ServiceImpl<ServerCatitemRelationDao, ServerCatitemRelationEntity> implements ServerCatitemRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ServerCatitemRelationEntity> page = this.page(
                new Query<ServerCatitemRelationEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}