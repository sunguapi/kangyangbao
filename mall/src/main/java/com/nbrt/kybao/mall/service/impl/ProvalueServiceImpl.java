package com.nbrt.kybao.mall.service.impl;

import com.nbrt.kybao.mall.model.entity.Provalue;
import com.nbrt.kybao.mall.mapper.ProvalueMapper;
import com.nbrt.kybao.mall.service.api.ProvalueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 属性值 服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Slf4j
@Service
@Transactional
public class ProvalueServiceImpl extends ServiceImpl<ProvalueMapper, Provalue> implements ProvalueService {

}
