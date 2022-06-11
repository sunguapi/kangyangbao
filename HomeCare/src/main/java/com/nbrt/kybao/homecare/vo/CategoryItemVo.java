package com.nbrt.kybao.homecare.vo;

import lombok.Data;

/**
 *  首页展示分类项数据
 */
@Data
public class CategoryItemVo {

    /**
     * 分类项id
     */
    private Long catItemId;
    /**
     * 图片地址
     */
    private String icon;
    /**
     * 分类项名称
     */
    private String catItemName;
}
