package com.nbrt.kybao.mall.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hjh
 * @date 2022/5/30 15:53
 */
@Data
public class OrderDetailVo implements Serializable {
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("收货人姓名")
    private String receiveName;
    @ApiModelProperty("收货人手机号")
    private String receiveMobile;
    @ApiModelProperty("收货人省份")
    private String receiveProvince;
    @ApiModelProperty("收货人市")
    private String receiveCity;
    @ApiModelProperty("收货人区/县")
    private String receiveDistrict;
    @ApiModelProperty("详细地址")
    private String receiveAddress;

    @ApiModelProperty("商品列表")
    private List<CommodityInfoVo> commodityInfoVoList =new ArrayList<>();

    @ApiModelProperty("总金额")
    private BigDecimal totalPrice;
    @ApiModelProperty("运费")
    private BigDecimal postage;
    @ApiModelProperty("共计")
    private BigDecimal orderAmount;

    @ApiModelProperty("支付编号")
    private String payNo;
    @ApiModelProperty("支付方式 微信、支付宝、银行、其他")
    private String paymentType;
    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;
    @ApiModelProperty("订单创建时间")
    private Date createTime;
    @ApiModelProperty("订单状态：-1 已取消，0 创建，1 未付款，2 已付款，3 未发货，4 已发货，5 已到货，6 待评价")
    private Integer orderStatus;

}
