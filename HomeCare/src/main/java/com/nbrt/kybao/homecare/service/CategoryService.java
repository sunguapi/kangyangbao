package com.nbrt.kybao.homecare.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.nbrt.kybao.homecare.entity.CategoryEntity;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.vo.CategoryVo;
import com.nbrt.kybao.homecare.vo.server.ChildVo;

import java.util.List;
import java.util.Map;

/**
 * 服务分类
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取首页数据
     * @return 返回所有分类以及分类下的所有分类项数据
     */
    List<CategoryVo> getWelcomeData();


    List<ChildVo> getLevel1Categorys();

}

