package com.nbrt.kybao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.entity.VipType;
import com.nbrt.kybao.mapper.VipTypeMapper;
import com.nbrt.kybao.service.VipTypeServie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sunjinbao
 * @date 2022/5/31
 */
@Service
public class VipTypeServiceImpl extends ServiceImpl<VipTypeMapper, VipType> implements VipTypeServie {

    @Resource
    private VipTypeMapper vipTypeMapper;
    @Override
    public List<VipType> selectVipType() {
        List<VipType> list = vipTypeMapper.selectList(null);
        return list;
    }

    @Override
    public Page<VipType> getPageList(Page<VipType> page) {
        return vipTypeMapper.getPageList(page);
    }
}
