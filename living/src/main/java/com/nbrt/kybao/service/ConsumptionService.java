package com.nbrt.kybao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.entity.Consumption;
import com.nbrt.kybao.vo.ConsumptionVo;

/**
 * @author sunjinbao
 * @date 2022/6/2
 */
public interface ConsumptionService extends IService<Consumption> {
    Page<ConsumptionVo> searchConsumptionList(String consumerSite, int pageNum, int pageSize);
}
