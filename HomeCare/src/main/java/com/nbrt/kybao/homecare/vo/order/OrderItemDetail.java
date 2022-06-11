package com.nbrt.kybao.homecare.vo.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDetail {

    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 服务时间
     */
    private String serverTime;
    /**
     * 服务项信息
     */
    private OrderServerItemVo orderServerItemVo;
    /**
     * 地址信息
     */
    private UserReceiveVo userReceiveVo;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 实付款
     */
    private BigDecimal RealPrice;
}
