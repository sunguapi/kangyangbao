package com.nbrt.kybao.user.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliYunConstant implements InitializingBean {

    @Value("${alicloud.access-key}")
    private String accessKeyId;
    @Value("${alicloud.secret-key}")
    private String accessKeySecret;
    @Value("${alicloud.templateCode}")
    private String templateCode;

    public static String ACCESS_KEY_ID;
    public static String SECRECT;
    public static String TEMPLATECODE;

    @Override
    public void afterPropertiesSet() {
        ACCESS_KEY_ID = accessKeyId;
        SECRECT = accessKeySecret;
        TEMPLATECODE = templateCode;
    }
}
