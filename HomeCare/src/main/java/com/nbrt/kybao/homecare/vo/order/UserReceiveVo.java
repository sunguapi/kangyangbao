package com.nbrt.kybao.homecare.vo.order;

import lombok.Data;

/**
 * 用户添加收获地址页面数据
 */
@Data
public class UserReceiveVo {

    /**
     * 用户编号
     */
    private String userNo;
    /**
     * 收货编号
     */
    private String receiveNo;
    /**
     * 收货人姓名
     */
    private String receiveName;
    /**
     * 收货人手机号
     */
    private String receivePhone;
    /**
     * 省
     */
    private String receiveProvince;
    /**
     * 市
     */
    private String receiveCity;
    /**
     * 区/县
     */
    private String receiveDistrict;
    /**
     * 详细地址
     */
    private String receiveAddress;
    /**
     * 是否默认地址
     */
    private Integer defaultOn;
}
