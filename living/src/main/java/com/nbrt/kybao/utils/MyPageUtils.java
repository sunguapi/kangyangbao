package com.nbrt.kybao.utils;


import lombok.Data;

import java.util.List;

@Data
public class MyPageUtils<T> {

    /**
     * 当前页
     */
    private Long currPage;
    /**
     * 总记录数
     */
    private Long totalPage;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Long totalCount;
    /**
     * 记录集合
     */
    private List<T> list;
}
