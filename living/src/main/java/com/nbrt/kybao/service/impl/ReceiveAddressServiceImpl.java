package com.nbrt.kybao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.entity.ReceiveAddress;
import com.nbrt.kybao.mapper.ReceiveAddressMapper;
import com.nbrt.kybao.service.ReceiveAddressService;
import org.springframework.stereotype.Service;

@Service
public class ReceiveAddressServiceImpl extends ServiceImpl<ReceiveAddressMapper, ReceiveAddress> implements ReceiveAddressService {
}
