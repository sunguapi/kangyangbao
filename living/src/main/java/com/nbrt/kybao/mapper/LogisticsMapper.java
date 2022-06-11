package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.Logistics;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @author sunjinbao
 * @date 2022/6/1
 */
@Mapper
@Repository
public interface LogisticsMapper extends BaseMapper<Logistics> {
    Page<Logistics> getPageList(Page<Logistics> page);
}
