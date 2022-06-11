package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品 属性
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Data
@TableName("tab_property")
@ApiModel(value = "Property对象", description = "商品 属性")
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("属性编号")
    @TableField("property_no")
    private String propertyNo;

    @ApiModelProperty("货物编号")
    @TableField("pro_no")
    private String proNo;

    @ApiModelProperty("属性名")
    @TableField("attribute_name")
    private String attributeName;

    @ApiModelProperty("规格内容")
    @TableField("standard_name")
    private String standardName;

    @ApiModelProperty("给属性库存")
    @TableField("property_stock")
    private Integer propertyStock;

    @ApiModelProperty("货物单价")
    @TableField("property_price")
    private BigDecimal propertyPrice;

    @ApiModelProperty("属性图片")
    @TableField("property_img")
    private String propertyImg;

}
