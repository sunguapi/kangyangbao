package com.nbrt.kybao.homecare.vo.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 确认订单服务项页面数据
 */
@Data
public class OrderServerItemVo {

    /**
     * 服务项id
     */
    private Long serverItemId;
    /**
     * 服务项图片
     */
    private String image;
    /**
     * 服务项名称
     */
    private String serverItemName;
    /**
     * 服务项价格
     */
    private BigDecimal serverItemPrice;
    /**
     * 服务项数量
     */
    private Integer serverItemCount;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 服务项介绍
     */
    private String descript;

}
