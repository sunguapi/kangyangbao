package com.nbrt.kybao.mall.service.impl;

import com.nbrt.kybao.mall.model.entity.Contents;
import com.nbrt.kybao.mall.mapper.ContentsMapper;
import com.nbrt.kybao.mall.service.api.ContentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品详情描述表 服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Slf4j
@Service
@Transactional
public class ContentsServiceImpl extends ServiceImpl<ContentsMapper, Contents> implements ContentsService {

}
