package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Data
@TableName("tab_orders")
@ApiModel(value = "Orders对象", description = "订单表")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("订单编号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("用户编号")
    @TableField("user_no")
    private String userNo;

    @ApiModelProperty("商品属性编号")
    @TableField("property_no")
    private String propertyNo;

    @ApiModelProperty("支付编号")
    @TableField("pay_no")
    private String payNo;

    @ApiModelProperty("收货编号")
    @TableField("receive_no")
    private String receiveNo;

    @ApiModelProperty("运费")
    @TableField("postage")
    private BigDecimal postage;

    @ApiModelProperty("快递单号")
    @TableField("delivery_no")
    private String deliveryNo;

    @ApiModelProperty("快递公司")
    @TableField("delivery_name")
    private String deliveryName;

    @ApiModelProperty("总金额")
    @TableField("total_price")
    private BigDecimal totalPrice;

    @ApiModelProperty("共计")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty("订单状态：-1 已取消，0 创建，1 未付款，2 已付款，3 未发货，4 已发货，5 已到货，6 待评价")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty("支付时间")
    @TableField("payment_time")
    private Date paymentTime;

    @ApiModelProperty("发货时间")
    @TableField("send_time")
    private Date sendTime;

    @ApiModelProperty("订单完成时间")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
