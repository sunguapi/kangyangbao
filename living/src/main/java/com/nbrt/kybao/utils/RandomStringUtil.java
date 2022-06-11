package com.nbrt.kybao.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class RandomStringUtil {

    //随机订单号
    public static String generateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        return (sdf.format(new Date()) + makeUUID(10)).toUpperCase();
    }
    //随机字符串
    public static String makeUUID(int len) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
    }

    public static String getRandomString(int length) {

        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);    //从62个字符中随机取其中一个
            sb.append(str.charAt(number));  //用取到的数当索引取字符加到length个数的字符串
        }

        return sb.toString();  //返回字符串
    }

}
