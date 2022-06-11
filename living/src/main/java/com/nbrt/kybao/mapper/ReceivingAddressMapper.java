package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbrt.kybao.entity.ReceivingAddress;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author hjh
 * @date 2022/6/2 21:43
 */
@Mapper
@Repository
public interface ReceivingAddressMapper extends BaseMapper<ReceivingAddress> {
}
