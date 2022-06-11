package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.Children;
import com.nbrt.kybao.vo.UserChildrenVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

/**
 * @author sunjinbao
 * @date 2022/6/1
 */
@Mapper
@Repository
public interface ChildrenMapper extends BaseMapper<Children> {
    Page<UserChildrenVo> searchChildrenById(
            @Param("page") Page<UserChildrenVo> page,
            @Param("primaryUserNo") String primaryUserNo
    );
}
