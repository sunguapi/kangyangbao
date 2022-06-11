package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sunjinbao
 * @date 2022/6/2
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户")
@TableName("tab_consumption")
public class Consumption implements Serializable {

    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
//    @ApiModelProperty(value = "会员id")
//    private Integer memberId;
    @ApiModelProperty(value = "佣金折扣")
    private BigDecimal consumerDiscount;
    @ApiModelProperty(value = "消费积分")
    private BigDecimal consumerBack;
    @ApiModelProperty(value = "功能场景")
    private String consumerSite;
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Data updateTime;
    @ApiModelProperty(value = "金额积分")
    private BigDecimal integralRate;
    @ApiModelProperty(value = "金额积分占比")
    private BigDecimal consumerBili;
    @ApiModelProperty(value = "会员级别")
    private String vipType;

}
