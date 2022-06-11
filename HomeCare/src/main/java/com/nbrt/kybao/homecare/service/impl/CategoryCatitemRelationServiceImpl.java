package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.nbrt.kybao.homecare.dao.CategoryCatitemRelationDao;
import com.nbrt.kybao.homecare.entity.CategoryCatitemRelationEntity;
import com.nbrt.kybao.homecare.service.CategoryCatitemRelationService;


@Service("categoryCatitemRelationService")
public class CategoryCatitemRelationServiceImpl extends ServiceImpl<CategoryCatitemRelationDao, CategoryCatitemRelationEntity> implements CategoryCatitemRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryCatitemRelationEntity> page = this.page(
                new Query<CategoryCatitemRelationEntity>().getPage(params),
                new QueryWrapper<CategoryCatitemRelationEntity>()
        );

        return new PageUtils(page);
    }

}