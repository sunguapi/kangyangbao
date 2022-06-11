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
 * 订单明细表
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Data
@TableName("tab_item")
@ApiModel(value = "Item对象", description = "订单明细表")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("明细表主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("订单编号")
    @TableField("order_id")
    private Integer orderId;

    @ApiModelProperty("用户编号")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("商品编号")
    @TableField("pro_id")
    private Integer proId;

    @ApiModelProperty("商品名称")
    @TableField("pro_name")
    private String proName;

    @ApiModelProperty("商品图片地址")
    @TableField("img_path")
    private String imgPath;

    @ApiModelProperty("商品单价")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("购买商品数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("总价")
    @TableField("total_price")
    private BigDecimal totalPrice;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
