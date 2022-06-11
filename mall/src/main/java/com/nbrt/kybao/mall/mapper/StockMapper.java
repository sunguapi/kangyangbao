package com.nbrt.kybao.mall.mapper;

import com.nbrt.kybao.mall.model.entity.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品库存 Mapper 接口
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

}
