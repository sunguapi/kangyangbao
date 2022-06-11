package com.nbrt.kybao.homecare.vo;

import lombok.Data;

import java.util.Date;

@Data
public class EvaluationVo {
    /**
     * 评价时间
     */
    private Date createTime;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 评论详情
     */
    private String detail;
}
