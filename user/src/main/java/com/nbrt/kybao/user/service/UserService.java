package com.nbrt.kybao.user.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.nbrt.kybao.user.entity.UserEntity;
import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.vo.user.UserLoginRespVo;
import com.nbrt.kybao.user.vo.UserLoginVo;
import com.nbrt.kybao.user.vo.user.UserRegistRespVo;

import java.util.Map;

/**
 * 用户表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 18:13:07
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //短信验证码发送
    void sendCode(String phoneNumber) throws Exception;


    //用户手机号登录
    UserLoginRespVo phoneLogin(String code,String phoneNum);

    //用户账号密码登录
    UserLoginRespVo usernameAndPwdLogin(UserLoginVo userLoginVo);

    //获取用户信息
    UserEntity getUserInfoByUsername(String username);

    //用户注册
    UserRegistRespVo regist(UserLoginVo userLoginVo);
}

