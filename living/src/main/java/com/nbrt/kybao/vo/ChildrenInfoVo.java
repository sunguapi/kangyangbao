package com.nbrt.kybao.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author sunjinbao
 * @date 2022/6/1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenInfoVo {

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
    private Double balance;
    @ApiModelProperty(value = "消费余额")
    private Double amountCon;
    @ApiModelProperty(value = "积分剩余")
    private Double remainPoint;


    @ApiModelProperty(value = "用户积分")
    private Double userIntegral;
    @ApiModelProperty(value = "关系级别")
    private int relationGrade;

    @ApiModelProperty(value = "会员类型")
    private String vipType;
}
