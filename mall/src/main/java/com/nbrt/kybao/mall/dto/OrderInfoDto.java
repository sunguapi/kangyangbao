package com.nbrt.kybao.mall.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *     订单管理传输对象
 * </p>
 * @author hjh
 * @date 2022/5/6 13:31
 */
@Data
@ApiModel("订单管理传输对象")
public class OrderInfoDto extends BaseDto{
    @ApiModelProperty(value = "商品名")
    private String proName;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("支付编号")
    private String payNo;
    @ApiModelProperty("支付类型")
    private String paymentType;
    @ApiModelProperty("商品主图")
    private String mainImg;
    @ApiModelProperty("商品最低价格")
    private BigDecimal price;
    @ApiModelProperty("购买商品的数量")
    private Integer proQuantity;
    @ApiModelProperty("运费")
    private BigDecimal postage;
    @ApiModelProperty("支付金额")
    private BigDecimal amount;
    @ApiModelProperty("订单创建时间")
    private Date createTime;
    @ApiModelProperty("订单状态：-1 已取消，0 创建，1 未付款，2 已付款，3 未发货，4 已发货，5 已到货，6 待评价")
    private Integer orderStatus;
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
}
