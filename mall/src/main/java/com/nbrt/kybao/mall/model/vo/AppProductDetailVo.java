package com.nbrt.kybao.mall.model.vo;

import com.nbrt.kybao.mall.model.entity.Img;
import com.nbrt.kybao.mall.model.entity.Property;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hjh
 * @date 2022/6/1 16:07
 */
@Data
public class AppProductDetailVo implements Serializable {
    @ApiModelProperty("轮播图片列表")
    private List<Img> imgListHead = new ArrayList<>(10);

    @ApiModelProperty("商品编号")
    private String proNo;
    @ApiModelProperty("商品主图")
    private String mainImg;
    @ApiModelProperty("商品品牌")
    private String brand;
    @ApiModelProperty("商品名称")
    private String proName;
    @ApiModelProperty("商品副标题")
    private String subTitle;
    @ApiModelProperty("商品最低价格")
    private BigDecimal price;
    @ApiModelProperty("商品折扣价格")
    private BigDecimal discountPrice;
    @ApiModelProperty("点击量")
    private Integer pv;
    @ApiModelProperty("商品限购")
    private String proLimit;
    @ApiModelProperty("已售")
    private Integer sold;
    @ApiModelProperty("上下架 0未上架 1上架")
    private Integer upDown;
    @ApiModelProperty("商品类型 0类似药品 1 类似衣物")
    private Integer proType;
    @ApiModelProperty("商品描述")
    private String proDesc;

    @ApiModelProperty("分类名称")
    private String cateName;

    @ApiModelProperty("商品属性列表")
    private List<Property> propertyList= new ArrayList<>(10);

    @ApiModelProperty("详情图片列表")
    private List<Img> imgListBottom= new ArrayList<>(10);
}
