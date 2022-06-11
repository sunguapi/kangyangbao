package com.nbrt.kybao.user.dao;

import com.nbrt.kybao.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 18:13:07
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
