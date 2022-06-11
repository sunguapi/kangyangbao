package com.nbrt.kybao.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hjh
 * @date 2022/5/30 16:10
 */
@Data
public class CommodityInfoVo implements Serializable {
    @ApiModelProperty("商品编号")
    private String proNo;
    @ApiModelProperty("商品名称")
    private String proName;
    @ApiModelProperty("商品主图")
    private String mainImg;
    @ApiModelProperty("商品单价")
    private BigDecimal commodityPrice;
    @ApiModelProperty("商品数量")
    private Integer proCount;
}
