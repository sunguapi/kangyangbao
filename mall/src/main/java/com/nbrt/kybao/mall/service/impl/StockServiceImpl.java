package com.nbrt.kybao.mall.service.impl;

import com.nbrt.kybao.mall.model.entity.Stock;
import com.nbrt.kybao.mall.mapper.StockMapper;
import com.nbrt.kybao.mall.service.api.StockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品库存 服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Slf4j
@Service
@Transactional
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

}
