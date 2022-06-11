package com.nbrt.kybao.user.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.nbrt.kybao.user.entity.MemberEntity;
import com.nbrt.kybao.user.utils.PageUtils;

import java.util.Map;

/**
 * 会员表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 18:13:07
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

