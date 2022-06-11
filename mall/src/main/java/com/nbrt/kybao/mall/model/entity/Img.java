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
 * 商品图片
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Data
@TableName("tab_img")
@ApiModel(value = "Img对象", description = "商品图片")
public class Img implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品编号")
    @TableField("pro_no")
    private String proNo;

    @ApiModelProperty("图片编号")
    @TableField("img_no")
    private String imgNo;

    @ApiModelProperty("图片路径")
    @TableField("img_path")
    private String imgPath;

    @ApiModelProperty("图片名称")
    @TableField("img_name")
    private String imgName;

    @ApiModelProperty("图片扩展名")
    @TableField("img_ext")
    private String imgExt;

    @ApiModelProperty("图片类型 0 商品轮播图 1 商品详情图片列表")
    @TableField("img_type")
    private Integer imgType;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
