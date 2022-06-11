package com.nbrt.kybao.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)//可以链式调用
@ToString
@ApiModel("(web端)修改客房状态DTO")
public class UpdateRoomStateDTO {
    @ApiModelProperty(value = "客房id")
    private int id;
    @ApiModelProperty(value = "状态(1使用中  2开放预约  3暂停使用)")
    private int roomState;
}
