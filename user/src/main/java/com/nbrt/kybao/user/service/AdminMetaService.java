package com.nbrt.kybao.user.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.nbrt.kybao.user.entity.AdminMetaEntity;
import com.nbrt.kybao.user.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-25 11:14:26
 */
public interface AdminMetaService extends IService<AdminMetaEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

