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
@ApiModel("(web端)查询酒店并分页-省市&(酒店名)DTO")
public class GetHotelPageDTO {
    @ApiModelProperty(value = "省(酒店)")
    private String hotelProvince;
    @ApiModelProperty(value = "市(酒店)")
    private String hotelCity;
    @ApiModelProperty(value = "酒店名称")
    private String hotelName;

}
