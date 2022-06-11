package com.nbrt.kybao.homecare.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.nbrt.kybao.homecare.entity.OrderEntity;
import com.nbrt.kybao.homecare.utils.MyPageUtils;
import com.nbrt.kybao.homecare.vo.order.*;

import java.util.Map;

/**
 * 订单表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-30 09:37:51
 */
public interface OrderService extends IService<OrderEntity> {

    //获取订单列表信息
    MyPageUtils<OrderListVo> queryPage(Map<String, Object> params);

    //订单确认
    OrderConfirmVo confirmOrder(String token, Long serverItemId);

    //订单提交
    void submitOrder(OrderSubmitVo orderSubmitVo);

    //订单详情
    OrderItemDetail getOrderDetail(String token);

    //取消订单
    void cancelOrder(String orderNo);

    //订单管理端详情
    OrderAdminDetailVo getAdminOrderDetail(String orderNo);

    //订单管理端退款详情
    OrderAdminRefoundVo getRefoundDetail(String orderNo);
}

