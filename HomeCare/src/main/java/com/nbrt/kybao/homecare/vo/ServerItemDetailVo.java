package com.nbrt.kybao.homecare.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 服务项详情数据
 */
@Data
public class ServerItemDetailVo {

    /**
     * 服务项id
     */
    private Long serverItemId;
    /**
     * 服务项图片集
     */
    private List<String> images;

    /**
     * 服务项价格
     */
    private BigDecimal price;
    /**
     * 描述
     */
    private String explain;
    /**
     * 服务项名称
     */
    private String serverItemName;

    /**
     * 销量
     */
    private Long saleCount;

    /**
     * 最近可约时间
     */
    private String appointmentTime;

    /**
     * 服务项详情
     */
    private String descript;

    /**
     * 当前服务项的所有评价
     */
    private List<EvaluationVo> evaluationVos;
    /**
     * 订购须知
     */
    private String dgxz;
}
