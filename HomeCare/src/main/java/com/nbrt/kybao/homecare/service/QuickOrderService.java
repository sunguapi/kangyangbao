package com.nbrt.kybao.homecare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.homecare.entity.QuickOrderEntity;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.vo.order.OrderConfirmVo;
import com.nbrt.kybao.homecare.vo.order.OrderQuickDataVo;
import com.nbrt.kybao.homecare.vo.order.OrderSubmitQuickVo;


import java.util.List;
import java.util.Map;

/**
 * 快捷订单表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-05 13:00:35
 */
public interface QuickOrderService extends IService<QuickOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //生成快捷订单
    void submitQuickOrder(OrderSubmitQuickVo orderSubmitQuickVo);

    //快捷订单列表
    List<OrderQuickDataVo> getQuickOrderList(String token);

    //快捷订单确认页
    public OrderConfirmVo confirmOrder(String id);
}

