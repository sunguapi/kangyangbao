package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "用户")
@TableName("tab_user")
public class User implements Serializable {

    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @ApiModelProperty(value = "用户编号")
    private String userNo;
    @ApiModelProperty(value = "实名")
    private String userName;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    @ApiModelProperty(value = "会员编号")
    private int userGrade;
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;
    @ApiModelProperty(value = "性别")
    private String userGender;
    @ApiModelProperty(value = "user_phone")
    private String userPhone;
    @ApiModelProperty(value = "身份证")
    private String userCard;
    @ApiModelProperty(value = "余额")
    private BigDecimal balance;
    @ApiModelProperty(value = "消费余额")
    private BigDecimal amountCon;
    @ApiModelProperty(value = "积分剩余")
    private BigDecimal remainPoint;
    @ApiModelProperty(value = "消费积分")
    private BigDecimal conPoints;
    @ApiModelProperty(value = "用户积分")
    private int userIntegral;
    private String userPassword;

}
