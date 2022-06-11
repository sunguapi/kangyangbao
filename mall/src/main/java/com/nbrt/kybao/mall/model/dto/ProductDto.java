package com.nbrt.kybao.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hjh
 * @date 2022/5/23 14:46
 */
@Data
@ApiModel("商品请求对象")
public class ProductDto implements Serializable {
    @ApiModelProperty(value = "一页几条数据",required = true)
    private String pageSize;
    @ApiModelProperty(value = "当前页",required = true)
    private String currentPage;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("类别名(查询一类时必须)")
    private String categoryName;
    @ApiModelProperty("查询字符串(非必须)")
    private String searchString;
}
