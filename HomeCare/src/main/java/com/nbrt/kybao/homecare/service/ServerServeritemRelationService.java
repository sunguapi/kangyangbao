package com.nbrt.kybao.homecare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.homecare.entity.ServerServeritemRelationEntity;
import com.nbrt.kybao.homecare.utils.PageUtils;

import java.util.Map;

/**
 * 服务服务项关联
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
public interface ServerServeritemRelationService extends IService<ServerServeritemRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

