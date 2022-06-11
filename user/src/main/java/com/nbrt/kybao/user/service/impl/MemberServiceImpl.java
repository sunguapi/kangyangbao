package com.nbrt.kybao.user.service.impl;

import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.utils.Query;
import com.nbrt.kybao.user.dao.MemberDao;
import com.nbrt.kybao.user.entity.MemberEntity;
import com.nbrt.kybao.user.service.MemberService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

}