package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Accessors(chain = true)//可以链式调用
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("评价")
@TableName("sojourn_room")
public class Room implements Serializable {
    @ApiModelProperty(value = "该客房所属的酒店的id")
    private int whoseId;
    @ApiModelProperty(value = "客房id")
    private int id;
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic(value = "0", delval = "1")
    private boolean isDeleted;
    @ApiModelProperty(value = "客房名称")
    private String roomName;
    @ApiModelProperty(value = "顾客创建客房时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date roomTime;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @ApiModelProperty(value = "特价")
    private BigDecimal bargainPrice;
    @ApiModelProperty(value = "数量")
    private int roomNum;
    @ApiModelProperty(value = "面积")
    private double area;
    @ApiModelProperty(value = "状态(1使用中  2开放预约  3暂停使用)")
    private int roomState;
    @ApiModelProperty(value = "图片")
    private String roomPicture;
    @ApiModelProperty(value = "餐费")
    private BigDecimal mealFee;
    @ApiModelProperty(value = "标签")
    private String label;
    @ApiModelProperty(value = "介绍")
    private String introduce;

}
