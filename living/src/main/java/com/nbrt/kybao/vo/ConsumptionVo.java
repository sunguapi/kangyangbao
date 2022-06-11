package com.nbrt.kybao.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionVo implements Serializable {

    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

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
