package com.nbrt.kybao.mall.model.constant;

/**
 * @author hjh
 * @date 2022/5/12 10:56
 */
public class KybConstant {

    /**
     * 最大推荐商品数
     */
    public static final Long RECOMMEND_MAX = 3L;

    public static final String MESSAGE_LOGIN_FAILED = "抱歉！账号密码错误！请重新输入！";
    public static final String MESSAGE_PHONE_NOT_EXIST = "该手机号未注册！";
    public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_USE = "抱歉！这个账号已经被使用了！";
    public static final String MESSAGE_ACCESS_FORBIDDEN = "请登录以后再访问！";
    public static final String MESSAGE_STRING_INVALIDATE = "字符串不合法！请不要传入空字符串！";
    public static final String MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE = "系统错误！登录账号不唯一！";
    public static final String MESSAGE_SYSTEM_ERROR_PHONE_NOT_UNIQUE = "系统错误！登录手机号不唯一！";
    public static final String MESSAGE_SYSTEM_ERROR = "系统错误！";
    public static final String MESSAGE_CANCEL_SUCCESS = "取消成功！";

    public static final String ATTR_NAME_EXCEPTION = "exception";
    public static final String ATTR_NAME_LOGIN_USER = "loginUser";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
}
