package com.nbrt.kybao.homecare.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单提交数据
 */
@Data
@ApiModel(value = "订单提交页面数据")
public class OrderSubmitVo {

    /**
     * token令牌
     */
    @ApiModelProperty(value = "token令牌",required = true)
    private String token;
    /**
     * 收货信息编号
     */
    @ApiModelProperty(value = "收货信息编号")
    private String receiveNo;
    /**
     * 服务时间
     */
    @ApiModelProperty(value = "服务时间")
    private String serverTime;
    /**
     * 服务项编号
     */
    @ApiModelProperty(value = "服务项编号")
    private String propertyNo;
    /**
     * 总价
     */
    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;
    /**
     *  优惠后价格
     */
    @ApiModelProperty(value = "优惠后价格")
    private BigDecimal realPrice;
    /**
     * 花费积分
     */
    @ApiModelProperty(value = "花费积分")
    private Integer Integral;
    /**
     * 积分优惠
     */
    @ApiModelProperty(value = "积分优惠")
    private BigDecimal IntegralDiscount;
    /**
     *  会员优惠价格
     */
    @ApiModelProperty(value = "会员优惠价格")
    private BigDecimal memberPrice;
}
