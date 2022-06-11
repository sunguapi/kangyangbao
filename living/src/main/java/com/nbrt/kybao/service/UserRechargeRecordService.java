package com.nbrt.kybao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.entity.UserRechargeRecord;
import com.nbrt.kybao.vo.UserRechargeRecordVo;

import java.util.List;

public interface UserRechargeRecordService extends IService<UserRechargeRecord> {

    List<UserRechargeRecordVo> searchUserRechargeRecord(String userNO);
}
