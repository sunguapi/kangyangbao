package com.nbrt.kybao.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)//可以链式调用
@ToString
@ApiModel("(web端)查询客房并分页DTO")
public class FindRoomPageDTO implements Serializable {
    @ApiModelProperty(value = "该客房所属的酒店的id")
    private int whoseId;
    @ApiModelProperty(value = "客房名称")
    private String roomName;
    @ApiModelProperty(value = "状态(1使用中  2开放预约  3暂停使用)")
    private int roomState;
}
