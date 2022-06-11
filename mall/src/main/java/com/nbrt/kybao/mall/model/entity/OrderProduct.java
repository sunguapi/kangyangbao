package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hjh
 * @date 2022/5/30 16:23
 */
@Data
@TableName("tab_order_product")
@ApiModel(value = "OrderProduct对象", description = "订单商品关联表")
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单商品关联表主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("订单编号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("属性编号")
    @TableField("property_no")
    private String propertyNo;

    @ApiModelProperty("商品数量")
    @TableField("pro_count")
    private Integer proCount;
}
