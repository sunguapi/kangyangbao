package com.nbrt.kybao.homecare.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 快捷订单首页数据
 */
@Data
public class OrderQuickDataVo {

    @ApiModelProperty("快捷订单名称")
    private String quickName;

    @ApiModelProperty("快捷订单图片地址")
    private String icon;

    @ApiModelProperty("快捷订单编号")
    private String id;
}
