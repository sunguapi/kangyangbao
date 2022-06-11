package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.homecare.dao.ServerServeritemRelationDao;
import com.nbrt.kybao.homecare.entity.ServerServeritemRelationEntity;
import com.nbrt.kybao.homecare.service.ServerServeritemRelationService;


@Service("serverServeritemRelationService")
public class ServerServeritemRelationServiceImpl extends ServiceImpl<ServerServeritemRelationDao, ServerServeritemRelationEntity> implements ServerServeritemRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ServerServeritemRelationEntity> page = this.page(
                new Query<ServerServeritemRelationEntity>().getPage(params),
                new QueryWrapper<ServerServeritemRelationEntity>()
        );

        return new PageUtils(page);
    }

}