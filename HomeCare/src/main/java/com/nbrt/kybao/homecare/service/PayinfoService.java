package com.nbrt.kybao.homecare.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.nbrt.kybao.homecare.entity.PayinfoEntity;
import com.nbrt.kybao.homecare.utils.PageUtils;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-01 13:58:47
 */
public interface PayinfoService extends IService<PayinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

