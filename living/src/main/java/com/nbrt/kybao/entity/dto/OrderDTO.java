package com.nbrt.kybao.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTO {
    @ApiModelProperty(value = "订单id")
    private int id;
    @ApiModelProperty(value = "客房数量")
    private int roomNum;
    @ApiModelProperty(value = "住客姓名")
    private String residentName;
    @ApiModelProperty(value = "住客手机号")
    private String residentPhone;
    @ApiModelProperty(value = "预计到达时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//另加上此行 便可填写输入2022-05-31 09:00:00格式
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arriveTime;
}
