package com.nbrt.kybao.homecare.dao;

import com.nbrt.kybao.homecare.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表
 * 
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-30 09:37:51
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
