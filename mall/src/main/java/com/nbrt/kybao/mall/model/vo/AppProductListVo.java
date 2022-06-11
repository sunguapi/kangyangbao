package com.nbrt.kybao.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hjh
 * @date 2022/5/30 17:56
 */
@Data
public class AppProductListVo implements Serializable {
    @ApiModelProperty("商品编号")
    private String proNo;
    @ApiModelProperty("分类名称")
    private String cateName;
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
    @ApiModelProperty("商品描述")
    private String proDesc;
}
