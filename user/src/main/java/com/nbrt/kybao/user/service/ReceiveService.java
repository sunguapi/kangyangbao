package com.nbrt.kybao.user.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.nbrt.kybao.user.entity.ReceiveEntity;
import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.vo.UserReceiveVo;

import java.util.List;
import java.util.Map;

/**
 * 收货信息表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 18:13:07
 */
public interface ReceiveService extends IService<ReceiveEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //添加收获地址
    void saveReceive(UserReceiveVo userReceiveVo);

    //获取收货地址列表
    List<UserReceiveVo> getReceiveList(String token);

    //获取默认收货地址
    UserReceiveVo getUserDefaultReceiveByUsername(String username);

    //地址信息修改
    void updateReceive(UserReceiveVo userReceiveVo);

    //根据收货编号获取收货地址
    UserReceiveVo getReceiveById(String receiveId);
}

