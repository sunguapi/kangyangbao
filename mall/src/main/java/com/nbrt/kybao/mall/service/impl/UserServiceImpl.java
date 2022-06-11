package com.nbrt.kybao.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.mall.mapper.UserMapper;
import com.nbrt.kybao.mall.model.entity.User;
import com.nbrt.kybao.mall.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hjh
 * @date 2022/5/24 11:23
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
