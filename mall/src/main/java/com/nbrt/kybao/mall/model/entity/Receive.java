package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 收货信息表
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Data
@TableName("tab_receive")
@ApiModel(value = "Receive对象", description = "收货信息表")
public class Receive implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("收货编号")
    @TableField("receive_no")
    private String receiveNo;

    @ApiModelProperty("用户编号")
    @TableField("user_no")
    private String userNo;

    @ApiModelProperty("收货人姓名")
    @TableField("receive_name")
    private String receiveName;

    @ApiModelProperty("收货人电话")
    @TableField("receive_phone")
    private String receivePhone;

    @ApiModelProperty("收货人手机号")
    @TableField("receive_mobile")
    private String receiveMobile;

    @ApiModelProperty("收货人省份")
    @TableField("receive_province")
    private String receiveProvince;

    @ApiModelProperty("收货人市")
    @TableField("receive_city")
    private String receiveCity;

    @ApiModelProperty("收货人区/县")
    @TableField("receive_district")
    private String receiveDistrict;

    @ApiModelProperty("详细地址")
    @TableField("receive_address")
    private String receiveAddress;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
