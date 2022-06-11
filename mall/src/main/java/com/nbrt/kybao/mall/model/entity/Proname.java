package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品属性表
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Getter
@Setter
@TableName("tab_proname")
@ApiModel(value = "Proname对象", description = "商品属性表")
public class Proname implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("属性名：颜色 版本 尺寸")
    @TableField("title")
    private String title;

    @ApiModelProperty("货物属性加价")
    @TableField("attribute_price")
    private Integer attributePrice;
}
