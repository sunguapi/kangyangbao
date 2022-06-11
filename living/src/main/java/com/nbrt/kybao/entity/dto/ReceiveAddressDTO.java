package com.nbrt.kybao.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)//可以链式调用
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveAddressDTO {
//    @ApiModelProperty(value = "id")
//    @TableId(value = "id",type = IdType.AUTO)
//    private int id;
    @ApiModelProperty(value = "用户编号")
    private String userNo;
    @ApiModelProperty(value = "收货信息编号")
    private String receiveNo;
    @ApiModelProperty(value = "收货人名称")
    private String receiveName;
    @ApiModelProperty(value = "收货人电话")
    private String receivePhone;
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
}
