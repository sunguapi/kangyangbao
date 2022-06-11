package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.Consumption;
import com.nbrt.kybao.vo.ConsumptionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @author sunjinbao
 * @date 2022/6/2
 */
@Mapper
@Repository
public interface ConsumptionMapper extends BaseMapper<Consumption> {
    Page<ConsumptionVo> searchConsumptionList(
            @Param("page") Page<ConsumptionVo> page,
            @Param("consumerSite") String consumerSite);
}
