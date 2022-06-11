package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hjh
 * @date 2022/5/24 14:02
 */
@Data
@TableName("tab_member")
@ApiModel(value = "Member对象", description = "会员表")
public class Member implements Serializable {

    private static final String ID="会员编号";
    private static final String MEMBER_NAME="会员名称";
    private static final String AMOUNT_STANDARD="金额标准";
    private static final String MEMBER_COLOR="展示颜色";
    private static final String MEMBER_IMAGE="会员卡图片";
    private static final String CREATE_TIME="创建时间";
    private static final String UPDATE_TIME="修改时间";
    private static final String DELETED="逻辑删除";

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(ID)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(MEMBER_NAME)
    @TableField( "member_name")
    private String memberName;

    @ApiModelProperty(AMOUNT_STANDARD)
    @TableField( "amount_standard")
    private BigDecimal amountStandard;

    @ApiModelProperty(MEMBER_COLOR)
    @TableField( "member_color")
    private String memberColor;

    @ApiModelProperty(MEMBER_IMAGE)
    @TableField( "member_image")
    private String memberImage;

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
