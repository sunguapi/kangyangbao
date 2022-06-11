package com.nbrt.kybao.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.mall.mapper.OrderProductMapper;
import com.nbrt.kybao.mall.model.entity.OrderProduct;
import com.nbrt.kybao.mall.service.api.OrderProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hjh
 * @date 2022/5/30 16:27
 */
@Slf4j
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements OrderProductService {
}
