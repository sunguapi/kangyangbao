package com.nbrt.kybao.user.vo;

import lombok.Data;

import java.util.List;

@Data
public class AdminMenuAndInfoVo {
    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 用户名
     */
    private String username;

    /**
     * 路由信息
     */
    private List<AdminRouterVo> adminRouterVoList;
}
