package com.nbrt.kybao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.entity.Logistics;

/**
 * @author sunjinbao
 * @date 2022/6/1
 */
public interface LogisticsService extends IService<Logistics> {
    Page<Logistics> getPageList(Page<Logistics> page);
}
