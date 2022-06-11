package com.nbrt.kybao.homecare.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 服务项页面数据
 */
@Data
public class ServerItemSaveVo {

    /**
     * 服务项id
     */
    private Long serverItemId;
    /**
     * 服务项名称
     */
    private String serverItemName;
    /**
     * 服务项价格
     */
    private BigDecimal price;
    /**
     * 销售总量
     */
    private Long saleCount;
    /**
     * 服务项描述
     */
    private String descript;
    /**
     * 上门时间
     */
    private String arrivalTime;
    /**
     * 图标地址
     */
    private String defaultImage;
    /**
     * 好评率
     */
    private Integer praiseRate;
    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer showStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
