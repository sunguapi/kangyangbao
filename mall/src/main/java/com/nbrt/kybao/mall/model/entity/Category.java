package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Data
@TableName("tab_category")
@ApiModel(value = "Category对象", description = "商品分类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品分类主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("0为最高分类 1 为二级分类 数字越大 分类越后")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("排序情况")
    @TableField("sort_order")
    private Integer sortOrder;

    @ApiModelProperty("分类名称")
    @TableField("title")
    private String title;

    @ApiModelProperty("状态： 0 正常 1 禁用")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
