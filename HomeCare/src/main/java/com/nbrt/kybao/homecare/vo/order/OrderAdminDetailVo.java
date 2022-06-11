package com.nbrt.kybao.homecare.vo.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单详情
 */
@Data
public class OrderAdminDetailVo {

    private String orderNo;
    private String payNo;
    private String payType;
    private BigDecimal realPrice;
    private BigDecimal disCount;
    private String createTime;
    private String nikeName;
    private String disCountType;
    private String receiveName;
    private String receivePhone;
    private String receiveCity;
    private String receiveAddress;
    private String receiveDistrict;
    private String receiveProvince;
    private String serverType;
    private String serverItemName;
    private String serverItemTime;
    private BigDecimal serverItemPrice;
    private String remark;
}
