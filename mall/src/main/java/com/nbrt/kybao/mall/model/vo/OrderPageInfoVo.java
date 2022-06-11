package com.nbrt.kybao.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hjh
 * @date 2022/5/11 9:59
 */
@Data
public class OrderPageInfoVo implements Serializable {
    @ApiModelProperty("一页几条数据")
    private String pageSize;
    @ApiModelProperty("当前页")
    private String currentPage;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("类别名")
    private String categoryName;
    @ApiModelProperty("查询字符串")
    private String searchString;
}
