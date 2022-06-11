package com.nbrt.kybao.mapper;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbrt.kybao.entity.UserRechargeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface UserRechargeRecordMapper extends BaseMapper<UserRechargeRecord> {


     List<UserRechargeRecord> searchUserRechargeRecord(@Param("userNo") String userNo, @Param("mouth") int mouth, @Param("lastTime") String lastTime, @Param("beforeTime") String beforeTime);

     List<Integer> searchMouth(@Param("userNo") String userNo, @Param("lastTime") String lastTime, @Param("beforeTime") String beforeTime);
}

