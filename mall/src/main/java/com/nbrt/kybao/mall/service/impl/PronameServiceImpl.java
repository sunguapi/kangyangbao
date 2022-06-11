package com.nbrt.kybao.mall.service.impl;

import com.nbrt.kybao.mall.model.entity.Proname;
import com.nbrt.kybao.mall.mapper.PronameMapper;
import com.nbrt.kybao.mall.service.api.PronameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品属性表 服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Slf4j
@Service
@Transactional
public class PronameServiceImpl extends ServiceImpl<PronameMapper, Proname> implements PronameService {

}
