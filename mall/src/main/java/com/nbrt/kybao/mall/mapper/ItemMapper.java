package com.nbrt.kybao.mall.mapper;

import com.nbrt.kybao.mall.model.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单明细表 Mapper 接口
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

}
