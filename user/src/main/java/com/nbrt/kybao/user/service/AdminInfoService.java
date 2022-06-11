package com.nbrt.kybao.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.user.entity.AdminInfoEntity;
import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.vo.UserLoginVo;

import java.util.Map;

/**
 * 
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-25 11:14:26
 */
public interface AdminInfoService extends IService<AdminInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //用户登录
    Map<Object, Object> login(UserLoginVo userLoginVo);

}

