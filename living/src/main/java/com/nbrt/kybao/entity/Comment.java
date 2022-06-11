package com.nbrt.kybao.entity;

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
import java.util.Date;

@Accessors(chain = true)//可以链式调用
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("评价")
@TableName("sojourn_comment")
public class Comment implements Serializable {
    @ApiModelProperty(value = "评价id")
    private int id;
    @ApiModelProperty(value = "评价内容")
    private String content;
    @ApiModelProperty(value = "评价时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contentTime;
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic(value = "0", delval = "1")
    private boolean isDeleted;
    @ApiModelProperty(value = "该评价所属酒店的酒店id")
    private int idsHotelId;
}
