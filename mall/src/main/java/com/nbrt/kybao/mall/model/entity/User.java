package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hjh
 * @date 2022/5/23 16:06
 */
@Data
@TableName("tab_user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final String ID="主键";
    private static final String USER_NO="用户编号";
    private static final String USER_PHONE="用户手机号";
    private static final String USER_PASSWORD="用户密码";
    private static final String USER_AVATAR="用户头像";
    private static final String USER_GENDER="用户性别";
    private static final String USER_MOTTO="用户个签（座右铭）";
    private static final String USER_CARD="用户身份证";
    private static final String USER_GRADE="用户会员等级";
    private static final String USER_INTEGRAL="用户积分";
    private static final String TOTAL_RECHARGE="用户总充值";
    private static final String RELATION_NO="关联关系编号";
    private static final String RELATION_GRADE="关联级别";
    private static final String CREATE_TIME="创建时间";
    private static final String UPDATE_TIME="修改时间";
    private static final String DELETED="逻辑删除";

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(ID)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(USER_NO)
    @TableField("user_no")
    private String userNo;

    @ApiModelProperty(USER_PHONE)
    @TableField("user_phone")
    private String userPhone;

    @ApiModelProperty(USER_PASSWORD)
    @TableField("user_password")
    private String userPassword;

    @ApiModelProperty(USER_AVATAR)
    @TableField("user_avatar")
    private String userAvatar;

    @ApiModelProperty(USER_GENDER)
    @TableField("user_gender")
    private String userGender;

    @ApiModelProperty(USER_MOTTO)
    @TableField("user_motto")
    private String userMotto;

    @ApiModelProperty(USER_CARD)
    @TableField("user_card")
    private String userCard;

    @ApiModelProperty(USER_GRADE)
    @TableField("user_grade")
    private Integer userGrade;

    @ApiModelProperty(USER_INTEGRAL)
    @TableField("user_integral")
    private Integer userIntegral;

    @ApiModelProperty(TOTAL_RECHARGE)
    @TableField("total_recharge")
    private BigDecimal totalRecharge;

    @ApiModelProperty(RELATION_NO)
    @TableField("relation_no")
    private String relationNo;

    @ApiModelProperty(RELATION_GRADE)
    @TableField("relation_grade")
    private Integer relationGrade;

    @ApiModelProperty(CREATE_TIME)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(UPDATE_TIME)
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(DELETED)
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
