package com.nbrt.kybao.homecare.vo.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单详情页面数据
 */
@Data
public class OrderDetailVo {

    /**
     * 订单编号
     */
    private String orderNo;
    /**
     *  支付编号
     */
    private String payNo;
    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 优惠方式
     */
    private String discountType;
    /**
     *
     */
}
