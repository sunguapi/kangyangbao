package com.nbrt.kybao.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.mall.mapper.MemberMapper;
import com.nbrt.kybao.mall.model.entity.Member;
import com.nbrt.kybao.mall.service.api.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hjh
 * @date 2022/5/24 14:55
 */
@Slf4j
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
}
