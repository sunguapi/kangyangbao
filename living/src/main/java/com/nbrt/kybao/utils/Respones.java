package com.nbrt.kybao.utils;

/**
 * http响应结果通用类
 */
public class Respones {                       //暂不用
    // 响应业务状态
    private Integer code;
    // 响应消息
    private String message;
    // 响应中的数据
    private Object data;

    public static Respones success(Object data) {
        return new Respones(data);
    }

    public static Respones success() {
        return new Respones(null);
    }

    public static Respones error(String msg) {
        return new Respones(500, msg, null);
    }

    public static Respones paramError(String msg) {
        return new Respones(201, msg, null);
    }


    public Respones(Object data) {
        this.code = 200;
        this.message = "SUCCESS";
        this.data = data;
    }

    public Respones(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}