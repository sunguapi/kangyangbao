package com.nbrt.kybao.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)//可以链式调用
@ToString
@ApiModel("(web端)修改售价管理-根据idDTO")
public class UpdatePriceByIdDTO {
    @ApiModelProperty(value = "酒店id")
    private int id;
    @ApiModelProperty(value = "价格")
    private double price;
    @ApiModelProperty(value = "会员价")
    private double memberPrice;
    @ApiModelProperty(value = "特价(活动价)")
    private double bargainPrice;

}
