package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sunjinbao
 * @date 2022/5/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_vip_type")
public class VipType implements Serializable {
    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "会员类型")
    private String vipType;
    @ApiModelProperty(value = "名称颜色")
    private String nameColor;
    @ApiModelProperty(value = "消费金额")
    private double amountCon;
    @ApiModelProperty(value = "图片地址")
    private String deAddress;

}
