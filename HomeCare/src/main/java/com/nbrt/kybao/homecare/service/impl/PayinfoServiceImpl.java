package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.homecare.dao.PayinfoDao;
import com.nbrt.kybao.homecare.entity.PayinfoEntity;
import com.nbrt.kybao.homecare.service.PayinfoService;


@Service("payinfoService")
public class PayinfoServiceImpl extends ServiceImpl<PayinfoDao, PayinfoEntity> implements PayinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PayinfoEntity> page = this.page(
                new Query<PayinfoEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}