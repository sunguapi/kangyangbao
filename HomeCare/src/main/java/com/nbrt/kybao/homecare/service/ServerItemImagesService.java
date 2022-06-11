package com.nbrt.kybao.homecare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.homecare.entity.ServerItemImagesEntity;
import com.nbrt.kybao.homecare.utils.PageUtils;

import java.util.Map;

/**
 * 服务项图片
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 14:51:20
 */
public interface ServerItemImagesService extends IService<ServerItemImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

