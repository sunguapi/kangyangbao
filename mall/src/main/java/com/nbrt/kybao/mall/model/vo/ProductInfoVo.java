package com.nbrt.kybao.mall.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hjh
 * @date 2022/5/11 16:11
 */
@Data
@ApiModel("商品列表展示对象")
public class ProductInfoVo implements Serializable {
    @ApiModelProperty("商品编号")
    private String proNo;
    @ApiModelProperty("商品名称")
    private String proName;
    @ApiModelProperty("分类名称")
    private String title;
    @ApiModelProperty("商品最低价格")
    private BigDecimal price;
    @ApiModelProperty("已售")
    private Integer sold;
    @ApiModelProperty("库存")
    private Integer stock;
    @ApiModelProperty("商品创建时间")
    private Date createTime;
    @ApiModelProperty("是否推荐 0不推荐 1推荐")
    private Integer recommend;
    @ApiModelProperty("是否下架")
    private Integer upDown;
}
