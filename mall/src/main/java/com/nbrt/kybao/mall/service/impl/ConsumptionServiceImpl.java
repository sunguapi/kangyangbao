package com.nbrt.kybao.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.mall.mapper.ConsumptionMapper;
import com.nbrt.kybao.mall.model.entity.Consumption;
import com.nbrt.kybao.mall.service.api.ConsumptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hjh
 * @date 2022/5/24 14:58
 */
@Slf4j
@Service
public class ConsumptionServiceImpl extends ServiceImpl<ConsumptionMapper , Consumption> implements ConsumptionService {
}
