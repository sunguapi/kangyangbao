package com.nbrt.kybao.user.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.nbrt.kybao.user.entity.AdminMetaEntity;
import lombok.Data;

import java.util.List;

@Data
public class AdminRouterVo {


    /**
     * 路由id
     */
    @TableId
    private Integer routerId;
    /**
     * 路径
     */
    private String path;
    /**
     * 组件名称
     */
    private String commponent;
    /**
     * 组件标题
     */
    private String name;
    /**
     * 转发地址
     */
    private String redirect;
    /**
     * 目标数据id
     */
    private AdminMetaEntity meta;
    /**
     * 是否隐藏
     */
    private Integer hidden;
    /**
     * 孩子
     */
    private List<AdminRouterVo> children;
}
