package com.nbrt.kybao.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class PayUtil {

    //appid
    private static final String APPID="appid";

    private static final String MCHID="mchid";
    //通知地址
    private static final String notify_url="";

    //用户下单
    public static String placeOrder(Map<String,Object> map){
        final String url = "https://api.mch.weixin.qq.com/v3/pay/transactions/app";

        MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        try {
            json.put("appid",APPID);   //appid
            json.put("mchid",MCHID);   //商户号
            json.put("description",map.get("description"));  //商品描述
            json.put("out_trade_no",map.get("out_trade_no"));    //商品订单号
            json.put("time_expire",map.get("time_expire"));   //订单失效时间
            json.put("notify_url",notify_url);    //通知地址

            JSONObject amount=new JSONObject();
            amount.put("total",map.get("total"));      //金额
            amount.put("currency","CNY");  //货币类型   CNY人民币

            json.put("amount",amount);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonType, String.valueOf(json));


        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type","application/json; charset=utf-8")
                .post(body)
                .build();
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }

       return null;
    }


}
