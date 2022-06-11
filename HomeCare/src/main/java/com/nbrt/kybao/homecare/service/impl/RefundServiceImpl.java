package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.dao.RefundDao;
import com.nbrt.kybao.homecare.entity.RefundEntity;
import com.nbrt.kybao.homecare.service.RefundService;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("refundService")
public class RefundServiceImpl extends ServiceImpl<RefundDao, RefundEntity> implements RefundService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RefundEntity> page = this.page(
                new Query<RefundEntity>().getPage(params),
                new QueryWrapper<RefundEntity>()
        );

        return new PageUtils(page);
    }

}