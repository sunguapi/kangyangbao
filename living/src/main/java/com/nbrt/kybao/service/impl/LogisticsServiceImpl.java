package com.nbrt.kybao.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.entity.Logistics;
import com.nbrt.kybao.mapper.LogisticsMapper;
import com.nbrt.kybao.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunjinbao
 * @date 2022/6/1
 */
@Service
public class LogisticsServiceImpl extends ServiceImpl<LogisticsMapper, Logistics> implements LogisticsService {

   @Autowired
   LogisticsMapper logisticsMapper;

    @Override
    public Page<Logistics> getPageList(Page<Logistics> page) {
        Page<Logistics>page1=logisticsMapper.getPageList(page);
        return page1;
    }
}
