package com.nbrt.kybao.homecare.exception;

/**
 * 返回结果状态码以及提示消息
 */
public enum CodeEnum {
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VALID_EXCEPTION(10001, "参数格式验证失败"),
    SMS_CODE_EXCEPTION(10002, "验证码获取频率太高，请稍后再试"),
    TO_MANY_REQUEST(10002, "请求流量过大，请稍后再试"),

    IDEMPOTENT_TOKEN_CREATE_EXCEPTION(10003, "令牌创建失败"),
    IDEMPOTENT_TOKEN_VERIFY_EXCEPTION(10003, "令牌校验失败"),

    PRODUCT_UP_EXCEPTION(11000, "商品上架异常"),
    USER_EXIST_EXCEPTION(15001, "存在相同的用户"),
    PHONE_EXIST_EXCEPTION(15002, "存在相同的手机号"),
    LOGINACCT_PASSWORD_EXCEPTION(15003, "账号或密码错误"),
    NO_STOCK_EXCEPTION(21000, "商品库存不足");

    private int code;
    private String msg;

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
