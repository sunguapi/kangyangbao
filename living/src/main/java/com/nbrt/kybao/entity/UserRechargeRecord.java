package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_recharge_record")
public class UserRechargeRecord {
    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @ApiModelProperty(value = "用户编号")
    private String userNo;
    @ApiModelProperty(value = "消费金额")
    private BigDecimal rechargeAmount;
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

}
