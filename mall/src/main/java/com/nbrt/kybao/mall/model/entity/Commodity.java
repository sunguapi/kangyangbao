package com.nbrt.kybao.mall.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hjh
 * @date 2022/5/30 10:58
 */
@Data
public class Commodity implements Serializable {
    @ApiModelProperty("属性")
    private String attributeName;
    @ApiModelProperty("规格")
    private String standardName;
}
