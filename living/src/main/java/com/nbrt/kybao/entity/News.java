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
import java.lang.reflect.Array;
import java.util.Date;

@Accessors(chain = true)//可以链式调用
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("新闻类")
@TableName("news")
public class News implements Serializable {

    @ApiModelProperty(value = "新闻id")
    private int id;
    @ApiModelProperty(value = "图片")
    private String images;
    @ApiModelProperty(value = "新闻标题")
    private String newsName;
    @ApiModelProperty(value = "新闻内容")
    private String newsContent;//采用longtest类型，没有字数限制，可填写图文
    @ApiModelProperty(value = "新闻作者")
    private String newsWriter;
    @ApiModelProperty(value = "发布时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @ApiModelProperty(value = "新闻类型(1=医疗服务; 2=运营管理; 3=公寓康养; 4=智能居家; 5=康养行业新闻; 6=国家康养政策; 7=康养宝新闻;)")
    private int newsType;
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic(value = "0", delval = "1")
    private boolean isDeleted;






}
