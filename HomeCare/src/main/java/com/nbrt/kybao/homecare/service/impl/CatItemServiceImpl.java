package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.homecare.dao.CatItemDao;
import com.nbrt.kybao.homecare.entity.CatItemEntity;
import com.nbrt.kybao.homecare.service.CatItemService;


@Service("catItemService")
public class CatItemServiceImpl extends ServiceImpl<CatItemDao, CatItemEntity> implements CatItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CatItemEntity> page = this.page(
                new Query<CatItemEntity>().getPage(params),
                new QueryWrapper<CatItemEntity>()
        );

        return new PageUtils(page);
    }

}