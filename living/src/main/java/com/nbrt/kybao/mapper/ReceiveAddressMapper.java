package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbrt.kybao.entity.ReceiveAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReceiveAddressMapper extends BaseMapper<ReceiveAddress> {
    void updateDefaultOn(@Param("userNo") String userNo);
}
