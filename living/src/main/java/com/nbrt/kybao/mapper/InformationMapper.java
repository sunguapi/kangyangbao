package com.nbrt.kybao.mapper;


import com.nbrt.kybao.entity.Information;
import com.nbrt.kybao.entity.ReceivingAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


/*
* 公司基本信息
* */
@Mapper
public interface InformationMapper {
    //注册接口
    int register(Information information);

    //查询手机是否存在
    String getPhone(String username);

    //通过手机号查询个人信息
    Map<String,Object> getInformation(String username);

    //设置修改密码
    int setPwd(String phone,String password);

    int receivingAddress(ReceivingAddress address);
}
