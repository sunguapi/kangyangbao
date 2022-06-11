package com.nbrt.kybao.mall.model.dto;

import com.nbrt.kybao.mall.model.entity.Img;
import com.nbrt.kybao.mall.model.entity.Property;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hjh
 * @date 2022/5/23 15:09
 */
@Data
@ApiModel("添加商品请求对象")
public class AddProductDto implements Serializable {
    @ApiModelProperty("商品编号")
    private String proNo;
    @ApiModelProperty("商品名称")
    private String proName;
    @ApiModelProperty("分类名称")
    private String cateName;
    @ApiModelProperty("商品主图")
    private String mainImage;
    @ApiModelProperty("商品最低价格")
    private BigDecimal price;
    @ApiModelProperty("商品描述")
    private String proDesc;
    @ApiModelProperty("商品限购")
    private Integer proLimit;
    @ApiModelProperty("商品类型 是衣物还药品类型")
    private Integer proType;

    @ApiModelProperty("商品属性集合")
    private List<Property> propertyList=new ArrayList<>();


    @ApiModelProperty("商品轮播图")
    private List<String> imgListHead=new ArrayList<>();
    @ApiModelProperty("商品详情图")
    private List<String> imgListBottom=new ArrayList<>();
}
