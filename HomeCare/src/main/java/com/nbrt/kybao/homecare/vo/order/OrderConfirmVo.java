package com.nbrt.kybao.homecare.vo.order;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 服务确认页面数据
 */
@Data
public class OrderConfirmVo {

    /**
     * 用户性别
     */
    @ApiModelProperty("用户性别")
    private String gender;
    /**
     * 收货地址信息
     */
    UserReceiveVo userReceiveVo;
    /**
     * 订单服务项封装数据
     */
    private OrderServerItemVo orderServerItemVo;
    /**
     * 会员优惠
     */
    private BigDecimal memberPrice;
    /**
     * 积分折扣
     */
    private BigDecimal consumerDiscount;
    /**
     *  积分总和
     */
    private Integer integralRate;
    /**
     * 订单真实价格
     */
    private BigDecimal realPrice;
    /**
     * 服务时间
     */
    private String serverTime;
}
