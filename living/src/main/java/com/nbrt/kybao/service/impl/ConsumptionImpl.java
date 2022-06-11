package com.nbrt.kybao.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.entity.Consumption;
import com.nbrt.kybao.mapper.ConsumptionMapper;
import com.nbrt.kybao.service.ConsumptionService;
import com.nbrt.kybao.vo.ConsumptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author sunjinbao
 * @date 2022/6/2
 */
@Service
public class ConsumptionImpl extends ServiceImpl<ConsumptionMapper, Consumption> implements ConsumptionService {

    @Autowired
    private ConsumptionMapper consumptionMapper;

    @Override
    public Page<ConsumptionVo> searchConsumptionList(String consumerSite, int pageNum, int pageSize) {

        Page<ConsumptionVo> page = new Page<>(pageNum, pageSize);

        Page<ConsumptionVo> pages = consumptionMapper.searchConsumptionList(page, consumerSite);

        return pages;
    }
}
