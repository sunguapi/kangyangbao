package com.nbrt.kybao.entity;

import com.baomidou.mybatisplus.annotation.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)//可以链式调用
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("酒店康养(实体类)")
@TableName("sojourn_hotel")
public class Hotel implements Serializable {
    @ApiModelProperty(value = "酒店id")
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @ApiModelProperty(value = "搜索值(可不填)")
    @TableField("search_value")
    private String searchValue;
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")//value = "" 默认的原值;delval = "" 删除后的值
    @TableLogic(value = "0", delval = "1")       //加@TableLogic的情况下:走 Update 表名 set 加注解的列=值 where del=值
    @TableField("is_deleted")
    private boolean isDeleted;                   //不加@TableLogic的情况下:走 delete from 表名 where del=值
    @ApiModelProperty(value = "省(酒店)")
    @TableField("hotel_province")
    private String hotelProvince;
    @ApiModelProperty(value = "市(酒店)")
    @TableField("hotel_city")
    private String hotelCity;
    @ApiModelProperty(value = "酒店图片(封面)")
    @TableField("hotel_picture")
    private String hotelPicture;
    @ApiModelProperty(value = "酒店名称")
    @TableField("hotel_name")
    private String hotelName;
    @ApiModelProperty(value = "酒店介绍")
    @TableField("hotel_introduce")
    private String hotelIntroduce;
    @ApiModelProperty(value = "酒店创建时间")
    private String hotelCreationTime;
    @ApiModelProperty(value = "添加酒店的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date hotelTime;
    @ApiModelProperty(value = "酒店详细地址")
    @TableField("hotel_detailed_address")
    private String hotelDetailedAddress;
    @ApiModelProperty(value = "酒店类型(1田园 2公寓 3酒店)")
    @TableField("hotel_type")
    private int hotelType;
    @ApiModelProperty(value = "是否推荐(0不推荐 1推荐)")
    @TableField("recommend")
    private int recommend;
    @ApiModelProperty(value = "改为推荐的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("recommend_time")
    private Date recommendTime;
    @ApiModelProperty(value = "酒店电话")
    @TableField("hotel_telephone")
    private String hotelTelephone;
    @ApiModelProperty(value = "房间图片")
    @TableField("room_picture")
    private String roomPicture;
    @ApiModelProperty(value = "所属地产环境")
    @TableField("environment")
    private String environment;
    @ApiModelProperty(value = "属性标签")
    @TableField("attribute_label")
    private String attributeLabel;
    @ApiModelProperty(value = "价格")
    @TableField("price")
    private double price;
    @ApiModelProperty(value = "会员价")
    @TableField("member_price")
    private double memberPrice;
    @ApiModelProperty(value = "特价(活动价)")
    @TableField("bargain_price")
    private double bargainPrice;
    @ApiModelProperty(value = "平米")
    @TableField("square_metre")
    private double squareMetre;
    @ApiModelProperty(value = "图文介绍")
    @TableField("image_text")
    private String imageText;
    @ApiModelProperty(value = "入住时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("start_time")
    private Date startTime;
    @ApiModelProperty(value = "离店时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("end_time")
    private Date endTime;
    @ApiModelProperty(value = "收藏(默认为0，已收藏为1)")
    @TableField("collect")
    private int collect;
    @ApiModelProperty(value = "酒店房间类型")
    @TableField("hotel_room_type")
    private String hotelRoomType;
    @ApiModelProperty(value = "房间数量")
    @TableField("room_num")
    private int roomNum;
    @ApiModelProperty(value = "租客数量")
    @TableField("people_num")
    private int peopleNum;
    @ApiModelProperty(value = "租客姓名")
    private String uName;
    @ApiModelProperty(value = "租客手机号")
    private String uPhone;
    @ApiModelProperty(value = "关键词")
    @TableField("room_key")
    private String roomKey;
    @ApiModelProperty(value = "预计到店")
    @TableField("expect_go_store")
    private String expectGoStore;
    @ApiModelProperty(value = "最终金钱")
    @TableField("all_end_money")
    private double allEndMoney;
    // 最小价格
    private double minPrice;
    // 最小特惠价格
    private double minMemberPrice;


}
