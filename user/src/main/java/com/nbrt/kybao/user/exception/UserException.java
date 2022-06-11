package com.nbrt.kybao.user.exception;

import lombok.Data;

@Data
public class UserException extends RuntimeException{

    private Integer code;
    private String msg;

    public UserException(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public UserException() {}

}
