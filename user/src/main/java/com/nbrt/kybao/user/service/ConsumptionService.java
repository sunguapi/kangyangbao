package com.nbrt.kybao.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.user.entity.ConsumptionEntity;
import com.nbrt.kybao.user.utils.PageUtils;

import java.util.Map;

/**
 * 消费标准表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 18:13:07
 */
public interface ConsumptionService extends IService<ConsumptionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

