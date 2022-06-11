package com.nbrt.kybao.mall.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author hjh
 * @date 2022/5/13 13:59
 */
public class StringUtils {


    /**
     * 生成16位不重复的随机数，含数字+大小写
     * @return string
     */
    private static String getGUID() {
        StringBuilder sb = new StringBuilder();
        //产生16位的强随机数
        Random rd = new SecureRandom();
        for (int i = 0; i < 16; i++) {
            //产生0-2的3位随机数
            int type = rd.nextInt(3);
            switch (type){
                case 0:
                    //0-9的随机数
                    sb.append(rd.nextInt(10));
                    break;
                case 1:
                    //ASCII在65-90之间为大写,获取大写随机
                    sb.append((char)(rd.nextInt(25)+65));
                    break;
                case 2:
                    //ASCII在97-122之间为小写，获取小写随机
                    sb.append((char)(rd.nextInt(25)+97));
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 21; i++) {
            System.out.println(getGUID());
        }
    }
}
