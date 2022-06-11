package com.nbrt.kybao.user.service.impl;

import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.utils.Query;
import com.nbrt.kybao.user.dao.AdminMetaDao;
import com.nbrt.kybao.user.entity.AdminMetaEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.user.service.AdminMetaService;


@Service("adminMetaService")
public class AdminMetaServiceImpl extends ServiceImpl<AdminMetaDao, AdminMetaEntity> implements AdminMetaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminMetaEntity> page = this.page(
                new Query<AdminMetaEntity>().getPage(params),
                new QueryWrapper<AdminMetaEntity>()
        );

        return new PageUtils(page);
    }

}