package com.nbrt.kybao.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hjh
 * @date 2022/6/2 20:42
 */
@Data
public class SojournHotelVo implements Serializable {
    private Integer id;
    private String attributeLabel;
    private String hotelPicture;
    private String hotelName;
    private Double memberPrice;
    private Double price;
}
