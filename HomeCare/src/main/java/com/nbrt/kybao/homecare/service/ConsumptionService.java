package com.nbrt.kybao.homecare.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.nbrt.kybao.homecare.entity.ConsumptionEntity;
import com.nbrt.kybao.homecare.utils.PageUtils;

import java.util.Map;

/**
 * 消费标准表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-04 17:35:22
 */
public interface ConsumptionService extends IService<ConsumptionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

