package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Data
@TableName("tab_product")
@ApiModel(value = "Product对象", description = "商品")
public class Product implements Serializable {

    private static final String ID="主键";
    private static final String CATE_ID="商品类型编号";
    private static final String PRO_NO="商品编号";
    private static final String KEYWORDS="关键字";
    private static final String PRO_DESC="商品描述";
    private static final String MAIN_IMG="商品主图";
    private static final String BRAND="商品品牌";
    private static final String PRO_NAME="商品名";
    private static final String SUB_TITLE="商品副标题";
    private static final String PRO_LIMIT="限购";
    private static final String PRICE="商品最低价";
    private static final String DISCOUNT_PRICE="商品折扣价";
    private static final String PV="商品点击量";
    private static final String SOLD="商品售出";
    private static final String STOCK="商品库存";
    private static final String RECOMMEND="商品是否推荐  0不推荐 1推荐";
    private static final String UP_DOWN="商品是否上架  0未上架 1上架";
    private static final String PRO_TYPE="商品类型  0类似药品 1 类似衣物";
    private static final String CREATE_TIME="创建时间";
    private static final String UPDATE_TIME="修改时间";
    private static final String VERSION="主键";
    private static final String DELETED="逻辑删除";


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(ID)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(CATE_ID)
    @TableField("cate_id")
    private Integer cateId;

    @ApiModelProperty(PRO_NO)
    @TableField("pro_no")
    private String proNo;

    @ApiModelProperty(KEYWORDS)
    @TableField("keywords")
    private String keywords;

    @ApiModelProperty(PRO_DESC)
    @TableField("pro_desc")
    private String proDesc;

    @ApiModelProperty(MAIN_IMG)
    @TableField("main_img")
    private String mainImg;

    @ApiModelProperty(BRAND)
    @TableField("brand")
    private String brand;

    @ApiModelProperty(PRO_NAME)
    @TableField("pro_name")
    private String proName;

    @ApiModelProperty(SUB_TITLE)
    @TableField("sub_title")
    private String subTitle;

    @ApiModelProperty(PRO_LIMIT)
    @TableField("pro_limit")
    private Integer proLimit;

    @ApiModelProperty(PRICE)
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(DISCOUNT_PRICE)
    @TableField("discount_price")
    private BigDecimal discountPrice;

    @ApiModelProperty(PV)
    @TableField("pv")
    private Integer pv;

    @ApiModelProperty(SOLD)
    @TableField("sold")
    private Integer sold;

    @ApiModelProperty(STOCK)
    @TableField("stock")
    private Integer stock;

    @ApiModelProperty(RECOMMEND)
    @TableField("recommend")
    private Integer recommend;

    @ApiModelProperty(UP_DOWN)
    @TableField("up_down")
    private Integer upDown;

    @ApiModelProperty(PRO_TYPE)
    @TableField("pro_type")
    private Integer proType;

    @ApiModelProperty(CREATE_TIME)
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(UPDATE_TIME)
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(VERSION)
    @TableField(value = "version",fill = FieldFill.INSERT_UPDATE)
    private Integer version;

    @ApiModelProperty(DELETED)
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

}
