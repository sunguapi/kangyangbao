package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbrt.kybao.entity.dto.ReceiveAddressDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReceiveAddressDTOMapper extends BaseMapper<ReceiveAddressDTO> {
}
