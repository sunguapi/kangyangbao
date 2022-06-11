package com.nbrt.kybao.homecare.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 管理端订单列表数据
 */
@Data
public class OrderListVo {

    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单创建时间
     */
    private String createTime;
    /**
     * 订单下单用户
     */
    private String userNo;
    /**
     * 订单优惠后金额
     */
    private BigDecimal realPrice;
    /**
     * 积分优惠
     */
    private BigDecimal integralDiscount;
    /**
     * 会员优惠
     */
    private BigDecimal memberPrice;
    /**
     * 订单状态
     */
    private Integer orderStatus;
}
