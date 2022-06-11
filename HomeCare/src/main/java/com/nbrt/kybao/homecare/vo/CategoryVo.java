package com.nbrt.kybao.homecare.vo;

import lombok.Data;


import java.util.List;

/**
 *  首页展示列表数据
 */
@Data
public class CategoryVo {

    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 该分类下面的所有分类项
     */
    private List<CategoryItemVo> categoryItemList;
}
