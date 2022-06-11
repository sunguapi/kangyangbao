package com.nbrt.kybao.service;

import com.nbrt.kybao.entity.Information;
import com.nbrt.kybao.entity.ReceivingAddress;

import java.util.Map;

public interface InformationService {
    //注册接口
    int register(Information information);

    //查询手机是否存在
    String getPhone(String username);

    Map<String,Object> getInformation(String username);

    //设置修改密码
    int setPwd(String phone,String password);


    boolean receivingAddress(ReceivingAddress address);
}
