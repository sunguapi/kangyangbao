package com.nbrt.kybao.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hjh
 * @date 2022/5/6 10:44
 */
@Data
@ApiModel("商品展示对象")
public class ProductInfoDto extends BaseDto{
    @ApiModelProperty(value = "商品名")
    private String proName;
    @ApiModelProperty(value = "商品数量")
    private Integer stock;
    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;
    @ApiModelProperty(value = "商品分类名称")
    private String title;
}
