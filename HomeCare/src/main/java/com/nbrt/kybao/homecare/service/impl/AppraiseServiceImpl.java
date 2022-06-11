package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.homecare.dao.AppraiseDao;
import com.nbrt.kybao.homecare.entity.AppraiseEntity;
import com.nbrt.kybao.homecare.service.AppraiseService;


@Service("appraiseService")
public class AppraiseServiceImpl extends ServiceImpl<AppraiseDao, AppraiseEntity> implements AppraiseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppraiseEntity> page = this.page(
                new Query<AppraiseEntity>().getPage(params),
                new QueryWrapper<AppraiseEntity>()
        );

        return new PageUtils(page);
    }

}