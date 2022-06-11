package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 支付信息表
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Data
@TableName("tab_payinfo")
@ApiModel(value = "Payinfo对象", description = "支付信息表")
public class Payinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("支付编号")
    @TableField("pay_no")
    private String payNo;

    @ApiModelProperty("订单编号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("用户编号")
    @TableField("user_no")
    private String userNo;

    @ApiModelProperty("支付金额")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty("支付方式 微信、支付宝、银行、其他")
    @TableField("payment_type")
    private String paymentType;

    @ApiModelProperty("支付状态 -1 取消，0 进行中，1支付成功")
    @TableField("pay_status")
    private Integer payStatus;

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
