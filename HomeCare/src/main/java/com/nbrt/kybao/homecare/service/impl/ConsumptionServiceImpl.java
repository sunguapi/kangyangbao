package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.homecare.dao.ConsumptionDao;
import com.nbrt.kybao.homecare.entity.ConsumptionEntity;
import com.nbrt.kybao.homecare.service.ConsumptionService;


@Service("consumptionService")
public class ConsumptionServiceImpl extends ServiceImpl<ConsumptionDao, ConsumptionEntity> implements ConsumptionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ConsumptionEntity> page = this.page(
                new Query<ConsumptionEntity>().getPage(params),
                new QueryWrapper<ConsumptionEntity>()
        );

        return new PageUtils(page);
    }

}