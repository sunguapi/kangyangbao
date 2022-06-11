package com.nbrt.kybao.homecare.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nbrt.kybao.homecare.entity.CatItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbrt.kybao.homecare.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 服务分类项
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@Mapper
public interface CatItemDao extends BaseMapper<CatItemEntity> {


}
