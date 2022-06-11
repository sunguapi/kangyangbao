package com.nbrt.kybao.homecare.vo.severItem;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 服务项修改页面数据
 */
@Data
public class ServerItemUpdateVo {
    private Long serverItemId;
    private String serverItemName;
    private BigDecimal serverTime;
    private BigDecimal serverPrice;
    private List<String> images;
    private String descript;
    private Long[] ids;
    private String dgxz;
}
