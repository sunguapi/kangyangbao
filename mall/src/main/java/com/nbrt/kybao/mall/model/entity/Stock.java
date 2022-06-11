package com.nbrt.kybao.mall.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品库存
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Data
@TableName("tab_stock")
@ApiModel(value = "Stock对象", description = "商品库存")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品编号")
    @TableField("com_id")
    private Integer comId;

    @ApiModelProperty("属性名属性值编号")
    @TableField("pro_id")
    private Integer proId;

    @ApiModelProperty("库存")
    @TableField("stock")
    private Integer stock;


}
