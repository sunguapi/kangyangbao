package com.nbrt.kybao.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionDTO {


    String vipType;
    double consumerDiscount;
    String consumerSite;
}
