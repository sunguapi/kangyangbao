package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbrt.kybao.entity.UserRechargeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRechargeRecordMapper extends BaseMapper<UserRechargeRecord> {

}
