package com.nbrt.kybao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.entity.Children;
import com.nbrt.kybao.mapper.ChildrenMapper;
import com.nbrt.kybao.mapper.VipTypeMapper;
import com.nbrt.kybao.service.ChildrenService;
import com.nbrt.kybao.vo.ChildrenInfoVo;
import com.nbrt.kybao.vo.UserChildrenVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sunjinbao
 * @date 2022/6/1
 */
@Service
public class ChildrenServiceImpl extends ServiceImpl<ChildrenMapper, Children> implements ChildrenService {

    @Autowired
    private ChildrenMapper childrenMapper;

    @Autowired
    private VipTypeMapper vipTypeMapper;
    @Override
    public Page<UserChildrenVo> searchChildrenById(String primaryUserNo, int pageNum, int pageSize) {

        Page<UserChildrenVo> page = new Page<>(pageNum, pageSize);
        Page<UserChildrenVo> pages = childrenMapper.searchChildrenById(page, primaryUserNo);
        return pages;
    }

//    @Override
//    public ChildrenInfoVo searchUserInfoByChildrenId(String childrenId) {
//        Children children = childrenMapper.selectById(childrenId);
//        ChildrenInfoVo childrenInfoVo = new ChildrenInfoVo();
//        childrenInfoVo.setVipType(vipTypeMapper.selectById(children.getUserGrade()).getVipType());
//        BeanUtils.copyProperties(children, childrenInfoVo);
//        return childrenInfoVo;
//    }
}
