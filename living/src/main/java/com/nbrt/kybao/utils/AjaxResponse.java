package com.nbrt.kybao.utils;

import com.nbrt.kybao.utils.CustomException;
import com.nbrt.kybao.utils.CustomExceptionType;
import lombok.Data;

@Data/**/
@SuppressWarnings("all")
public class AjaxResponse<T> {
    private boolean isok;
    private int code;
    private String message;
    private T data;



    public AjaxResponse() {   //小帮

    }

    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CustomException e) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        if(e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(e.getMessage());
        }else if(e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()){
            resultBean.setMessage(e.getMessage() );
        }else{
            resultBean.setMessage("系统出现未知异常，请联系管理员电话：13756108x进行处理!");
        }
        return resultBean;
    }

    public static AjaxResponse error(String message) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(500);
        resultBean.setMessage(message);
        return resultBean;
    }

//    public static AjaxResponse success(String message) {
//        AjaxResponse resultBean = new AjaxResponse();
//        resultBean.setIsok(true);
//        resultBean.setCode(200);
//        resultBean.setMessage(message);
//        return resultBean;
//    }

    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }

    public static AjaxResponse success(Object data,String message) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }

    public static AjaxResponse success(int code,Object data,String message) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(code);
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }

    public static AjaxResponse errorFoToken(String message,Integer code) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(code);
        resultBean.setMessage(message);
        return resultBean;
    }



}
