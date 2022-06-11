package com.nbrt.kybao.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author hjh
 * @date 2022/5/6 10:45
 */
@Getter
@Setter
public class BaseDto implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;
}
