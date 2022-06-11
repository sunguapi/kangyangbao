package com.nbrt.kybao.user.service.impl;

import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.utils.Query;
import com.nbrt.kybao.user.dao.ConsumptionDao;
import com.nbrt.kybao.user.entity.ConsumptionEntity;
import com.nbrt.kybao.user.service.ConsumptionService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("consumptionService")
public class ConsumptionServiceImpl extends ServiceImpl<ConsumptionDao, ConsumptionEntity> implements ConsumptionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ConsumptionEntity> page = this.page(
                new Query<ConsumptionEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}