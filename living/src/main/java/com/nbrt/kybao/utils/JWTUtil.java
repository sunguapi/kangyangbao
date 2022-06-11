package com.nbrt.kybao.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private static final String SIGN = "ADwdlkmdDMKS1";

    /**
     * 生成token
     * @param map 存放的数据
     * @return 返回token header.payload.sing
     */
    public static String getToken(Map<String, String> map) throws Exception {

        Calendar instance = Calendar.getInstance();
        //默认七天过期
        instance.add(Calendar.DATE, 7);
        JWTCreator.Builder builder = JWT.create();
        //数据
        map.forEach(builder::withClaim);
        //指定过期时间
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 验证token合法性
     * @param token 需要验证的token
     */
    public static void verify(String token) throws Exception {
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     * 获取token信息
     * @param token 需要获取token的信息
     * @return map的方式返回payload里面的数据
     */
    public static Map<String, String> getTokenInfo(String token) throws Exception {
        Map<String, String> map = new HashMap<>();
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token).getClaims().forEach((k, v) -> {
            map.put(k, v.asString());
        });
        return map;
    }
}
