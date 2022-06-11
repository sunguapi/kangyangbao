package com.nbrt.kybao.config;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.nbrt.kybao.utils.Respones;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class JwtConfig {

        public static final int OK = 200;

        @ExceptionHandler({JWTVerificationException.class})
        public Respones handleException(Throwable e) {
            if (e instanceof SignatureVerificationException) {
                return new Respones(4000, e.getMessage(), "无效签名");
            } else if (e instanceof TokenExpiredException) {
                return new Respones(4001, e.getMessage(), "token过期");
            } else if (e instanceof AlgorithmMismatchException) {
                return new Respones(4002, e.getMessage(), "算法不一致");
            } else if (e instanceof Exception) {
                return new Respones(4003, e.getMessage(), "token无效");
            }
            return new Respones(200,e.getMessage(),e.getMessage());
        }

}
