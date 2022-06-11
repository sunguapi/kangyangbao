package com.nbrt.kybao.user.vo.user;


import lombok.Data;

/**
 * 用户注册返回数据
 */
@Data
public class UserRegistRespVo {
    /**
     * token令牌
     */
    private String token;
    /**
     * 注册情况
     */
    private Integer code;
}
