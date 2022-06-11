package com.nbrt.kybao.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hjh
 * @date 2022/5/30 15:27
 */
@Data
public class OrderListVo implements Serializable {
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("订单金额")
    private BigDecimal amount;
    @ApiModelProperty("支付单号")
    private String payNo;
    @ApiModelProperty("支付方式")
    private String paymentType;
    @ApiModelProperty("订单创建时间")
    private Date createTime;
    @ApiModelProperty("收货人姓名")
    private String receiveName;
    @ApiModelProperty("收货人手机号")
    private String receivePhone;
    @ApiModelProperty("订单状态：-1 已取消，0 创建，1 未付款，2 已付款，3 未发货，4 已发货，5 已到货，6 待评价")
    private Integer orderStatus;
}
