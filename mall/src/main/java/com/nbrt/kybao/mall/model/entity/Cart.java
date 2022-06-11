package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Data
@TableName("tab_cart")
@ApiModel(value = "Cart对象", description = "购物车表")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户编号")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("商品编号")
    @TableField("pro_id")
    private Integer proId;

    @ApiModelProperty("产品数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("是否勾选，0 否 1 是")
    @TableField("checked")
    private Integer checked;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
