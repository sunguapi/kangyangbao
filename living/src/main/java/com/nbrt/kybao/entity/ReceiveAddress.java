package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tab_receive")
public class ReceiveAddress {
    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @ApiModelProperty(value = "用户编号")
    private String userNo;
    @ApiModelProperty(value = "收货信息编号")
    private String receiveNo;
    @ApiModelProperty(value = "收货人名称")
    private String receiveName;
    @ApiModelProperty(value = "收货人电话")
    private String receivePhone;
    @ApiModelProperty(value = "收货人座机号")
    private String receiveMobile;
    @ApiModelProperty(value = "收货人省份")
    private String receiveProvince;
    @ApiModelProperty(value = "收货人市")
    private String receiveCity;
    @ApiModelProperty(value = "收货人区/县")
    private String receiveDistrict;
    @ApiModelProperty(value = "详细地址")
    private String receiveAddress;
    @ApiModelProperty(value = "是否默认选中,1是默认选中；0是未选中")
    private int defaultOn;
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    @ApiModelProperty(value = "mp逻辑删除")
    private int deleted;

//    @ApiModelProperty(value = "所有地址串")
//    private String allreceiveAddress;

}
