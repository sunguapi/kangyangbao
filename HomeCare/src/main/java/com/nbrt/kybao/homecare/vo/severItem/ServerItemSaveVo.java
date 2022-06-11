package com.nbrt.kybao.homecare.vo.severItem;

import com.nbrt.kybao.homecare.vo.ServerListVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 服务项保存页面数据
 */
@Data
public class ServerItemSaveVo {

    private Long serverItemId;
    private String serverItemName;
    private BigDecimal serverTime;
    private BigDecimal serverPrice;
    private List<String> images;
    private String descript;
    private Long serverId;
    private String dgxz;
}
