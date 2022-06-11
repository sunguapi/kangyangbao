package com.nbrt.kybao.homecare.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.nbrt.kybao.homecare.entity.RefundEntity;
import com.nbrt.kybao.homecare.utils.PageUtils;


import java.util.Map;

/**
 * 退款信息表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-04 11:11:42
 */
public interface RefundService extends IService<RefundEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

