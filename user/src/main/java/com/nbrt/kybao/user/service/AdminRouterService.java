package com.nbrt.kybao.user.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.nbrt.kybao.user.entity.AdminRouterEntity;
import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.vo.AdminMenuAndInfoVo;

import java.util.Map;

/**
 * 
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-25 11:14:26
 */
public interface AdminRouterService extends IService<AdminRouterEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //获取路由信息
    AdminMenuAndInfoVo getRouterInfo(String token);
}

