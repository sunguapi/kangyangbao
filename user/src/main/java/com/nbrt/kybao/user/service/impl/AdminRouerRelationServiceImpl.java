package com.nbrt.kybao.user.service.impl;

import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.user.dao.AdminRouerRelationDao;
import com.nbrt.kybao.user.entity.AdminRouerRelationEntity;
import com.nbrt.kybao.user.service.AdminRouerRelationService;


@Service("adminRouerRelationService")
public class AdminRouerRelationServiceImpl extends ServiceImpl<AdminRouerRelationDao, AdminRouerRelationEntity> implements AdminRouerRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminRouerRelationEntity> page = this.page(
                new Query<AdminRouerRelationEntity>().getPage(params),
                new QueryWrapper<AdminRouerRelationEntity>()
        );

        return new PageUtils(page);
    }

}