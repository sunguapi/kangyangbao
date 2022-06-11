package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.VipType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author sunjinbao
 * @date 2022/5/31
 */
@Mapper
@Repository
public interface VipTypeMapper extends BaseMapper<VipType> {
    Page<VipType> getPageList(Page<VipType> page);
}
