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
 * @date 2022/5/24 14:12
 */
@Data
@TableName("tab_consumption")
@ApiModel(value = "Consumption对象", description = "消费标准表")
public class Consumption implements Serializable {

    private static final String ID="消费编号";
    private static final String MEMBER_ID="会员编号";
    private static final String CONSUMER_DISCOUNT="消费折扣";
    private static final String CONSUMER_BACK="消费返还";
    private static final String CONSUMER_SITE="消费场地";
    private static final String CREATE_TIME="创建时间";
    private static final String UPDATE_TIME="修改时间";
    private static final String DELETED="逻辑删除";

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(ID)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(MEMBER_ID)
    @TableField("member_id")
    private Integer memberId;

    @ApiModelProperty(CONSUMER_DISCOUNT)
    @TableField("consumer_discount")
    private BigDecimal consumerDiscount;

    @ApiModelProperty(CONSUMER_BACK)
    @TableField("consumer_back")
    private BigDecimal consumerBack;

    @ApiModelProperty(CONSUMER_SITE)
    @TableField("consumer_site")
    private Integer consumerSite;

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
