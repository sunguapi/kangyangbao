package com.nbrt.kybao.homecare.vo;

import lombok.Data;

@Data
public class ServerListVo {
    /**
     * 服务id
     */
    private Long serverId;
    /**
     * 服务名称
     */
    private String serverName;
    /**
     * 服务图标
     */
    private String icon;

}
