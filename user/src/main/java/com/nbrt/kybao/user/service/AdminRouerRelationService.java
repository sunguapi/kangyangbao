package com.nbrt.kybao.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.user.entity.AdminRouerRelationEntity;
import com.nbrt.kybao.user.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-25 13:59:09
 */
public interface AdminRouerRelationService extends IService<AdminRouerRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

