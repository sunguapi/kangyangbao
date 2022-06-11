package com.nbrt.kybao.mall.service.impl;

import com.nbrt.kybao.mall.model.entity.Property;
import com.nbrt.kybao.mall.mapper.PropertyMapper;
import com.nbrt.kybao.mall.service.api.PropertyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品 属性 服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Slf4j
@Service
@Transactional
public class PropertyServiceImpl extends ServiceImpl<PropertyMapper, Property> implements PropertyService {

}
