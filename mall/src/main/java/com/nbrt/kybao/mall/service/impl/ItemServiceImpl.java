package com.nbrt.kybao.mall.service.impl;

import com.nbrt.kybao.mall.model.entity.Item;
import com.nbrt.kybao.mall.mapper.ItemMapper;
import com.nbrt.kybao.mall.service.api.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Slf4j
@Service
@Transactional
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

}
