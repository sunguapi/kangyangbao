package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Accessors(chain = true)//可以链式调用
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("订单")
@TableName("sojourn_order")
public class Order implements Serializable {

        @ApiModelProperty(value = "订单id")
        private int id;
        @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
        @TableLogic(value = "0", delval = "1")
        private int isDeleted;
        @ApiModelProperty(value = "订单编号")
        private String orderNo;
        @ApiModelProperty(value = "客房价格")
        private double price;
        @ApiModelProperty(value = "客房特价")
        private double bargainPrice;
        @ApiModelProperty(value = "酒店名称")
        private String hotelName;
        @ApiModelProperty(value = "客房名称")
        private String roomName;
        @ApiModelProperty(value = "酒店图片")
        private String hotelPicture;
        @ApiModelProperty(value = "酒店地址信息")
        private String hotelAddress;
        @ApiModelProperty(value = "酒店电话")
        private String hotelPhone;
        @ApiModelProperty(value = "标签")
        private String label;
        @ApiModelProperty(value = "客房数量")
        private int roomNum;
        @ApiModelProperty(value = "住客姓名")
        private String residentName;
        @ApiModelProperty(value = "住客手机号")
        private String residentPhone;
        @ApiModelProperty(value = "入住人数")
        private int residentNum;
        @ApiModelProperty(value = "预计到达时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//另加上此行 便可填写输入2022-05-31 09:00:00格式
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date arriveTime;
        @ApiModelProperty(value = "入住时间")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date inTime;
        @ApiModelProperty(value = "离店时间")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date outTime;
        @ApiModelProperty(value = "居住天数")
        private int days;
        @ApiModelProperty(value = "费用信息")
        private double expenseInformation;
        @ApiModelProperty(value = "房费")
        private double roomFee;
        @ApiModelProperty(value = "餐费")
        private double mealFee;
        @ApiModelProperty(value = "会员优惠")
        private double memberBenefit;
        @ApiModelProperty(value = "积分抵扣")
        private double integralDeduct;
        @ApiModelProperty(value = "积分抵扣金额")
        private double integralDeductPrice;
        @ApiModelProperty(value = "已优惠价格")
        private double deductFee;
        @ApiModelProperty(value = "总计")
        private double totalFee;
        @ApiModelProperty(value = "住客信息(姓名,身份,手机,房号)")
        private String fourlong;
        @ApiModelProperty(value = "订单状态(0.全部订单 1.支付中 2.支付完成 3.已完成 4.已取消 5.退款审核)")//0.全部订单 可能待改
        private int state;

        @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date createTime;
        @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.UPDATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date updateTime;


        private String userNo;


    }
