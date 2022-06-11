package com.nbrt.kybao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.entity.VipType;

import java.util.List;

/**
 * @author sunjinbao
 * @date 2022/5/31
 */
public interface VipTypeServie extends IService<VipType> {
    List<VipType> selectVipType();

    Page<VipType> getPageList(Page<VipType> page);
}
