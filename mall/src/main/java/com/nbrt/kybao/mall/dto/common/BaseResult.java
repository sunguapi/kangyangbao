package com.nbrt.kybao.mall.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hjh
 * @date 2022/5/5 15:59
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult implements Serializable {
    @ApiModelProperty(value = "调用状态码", notes = "200=成功,5200=未知错误")
    private Integer code;
    @ApiModelProperty(value = "调阅状态说明", notes = "如果调用失败，对应失败描述")
    private String message;
}
