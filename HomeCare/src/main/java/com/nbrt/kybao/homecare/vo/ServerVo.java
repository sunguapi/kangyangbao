package com.nbrt.kybao.homecare.vo;


import lombok.Data;

import java.util.List;


/**
 * 服务列表数据
 */
@Data
public class ServerVo {

    /**
     * 分类项名称
     */
    private String categoryName;

    /**
     * 是否默认选中
     */
    private Long defaultOn;

    /**
     * 服务信息相关
     */
    List<ServerListVo> serverListVos;

    /**
     * 当前服务下面的所有服务项
     */
    private List<ServerItemSaveVo> serverItemVos;
}
