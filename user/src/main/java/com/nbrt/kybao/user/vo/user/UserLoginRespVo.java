package com.nbrt.kybao.user.vo.user;

import lombok.Data;

import java.util.Map;

/**
 * 前台用户登录成功返回的响应数据
 */
@Data
public class UserLoginRespVo {

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nikeName;

    /**
     * 用户手机号码
     */
    private String userPhone;

    /**
     * 用户性别
     */
    private String userGender;

    /**
     * 微信是否绑定
     */
    private Boolean weChatBinding;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 面部是否采集
     */
    private Boolean faceCapture;

    /**
     * 返回的状态
     */
    private Integer code;

    /**
     * 登录令牌token
     */
    private String token;
}
