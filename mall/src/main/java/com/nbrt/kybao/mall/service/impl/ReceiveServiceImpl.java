package com.nbrt.kybao.mall.service.impl;

import com.nbrt.kybao.mall.model.entity.Receive;
import com.nbrt.kybao.mall.mapper.ReceiveMapper;
import com.nbrt.kybao.mall.service.api.ReceiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 收获信息表 服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Slf4j
@Service
@Transactional
public class ReceiveServiceImpl extends ServiceImpl<ReceiveMapper, Receive> implements ReceiveService {

}
