package com.nbrt.kybao.user.component;
// This file is auto-generated, don't edit it. Thanks.

import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;
import com.aliyun.teautil.models.*;
import com.nbrt.kybao.user.constant.AliYunConstant;
import com.nbrt.kybao.user.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Component
public class SMSComponent {

    /**
     * 使用AK&SK初始化账号Client
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        System.out.println(AliYunConstant.ACCESS_KEY_ID);
        System.out.println(AliYunConstant.SECRECT);
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(AliYunConstant.ACCESS_KEY_ID)
                // 您的AccessKey Secret
                .setAccessKeySecret(AliYunConstant.SECRECT);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void sendCode(String phoneNum,String code) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = SMSComponent.createClient();
        System.out.println("phone=>" + phoneNum + "code==>" + code + AliYunConstant.TEMPLATECODE);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setPhoneNumbers(phoneNum)
                .setTemplateCode(AliYunConstant.TEMPLATECODE)
                .setTemplateParam("{code:" + code + "}");
        RuntimeOptions runtime = new RuntimeOptions();
        // 复制代码运行请自行打印 API 的返回值
        client.sendSmsWithOptions(sendSmsRequest, runtime);
    }
}