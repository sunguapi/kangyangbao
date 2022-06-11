package com.nbrt.kybao.homecare.vo.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息数据
 */
@Data
public class UserInfoVo {
    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 用户编号
     */
    private String userNo;
    /**
     * 手机号码
     */
    private String userPhone;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 性别
     */
    private String userGender;
    /**
     * 座右铭
     */
    private String userMotto;
    /**
     * 身份证
     */
    private String userCard;
    /**
     * 会员等级
     */
    private Long userGrade;
    /**
     * 用户积分
     */
    private Integer userIntegral;
    /**
     * 总充值
     */
    private BigDecimal totalRecharge;
    /**
     * 关联关系编号 向上一代关联
     */
    private String relationNo;
    /**
     * 关系级别
     */
    private Integer relationGrade;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 逻辑删除
     */
    private Integer deleted;

    /**
     * 用户昵称
     */
    private String nikeName;

    /**
     * 微信是否绑定
     */
    private Boolean wechatBinding;

    /**
     * 面部是否采集
     */
    private Boolean faceCapture;

}
