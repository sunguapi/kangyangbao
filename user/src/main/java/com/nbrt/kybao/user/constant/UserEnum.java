package com.nbrt.kybao.user.constant;

/**
 * 用户登录注册枚举类
 */
public enum UserEnum {
    LOGIN_SUCCESS(200,"登录成功"),
    PASSWORD_ERROR(1,"密码错误"),
    USER_NOT_EXIST(2,"用户不存在"),
    CODE_ERROR(3,"短信验证码错误"),
    USER_EXISTED(4,"用户已存在"),
    USER_REGIST_SUCCESS(5,"用户注册成功");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;

    UserEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
