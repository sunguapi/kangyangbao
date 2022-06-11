package com.nbrt.kybao.service.impl;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.entity.UserRechargeRecord;
import com.nbrt.kybao.mapper.UserRechargeRecordMapper;
import com.nbrt.kybao.service.UserRechargeRecordService;
import com.nbrt.kybao.vo.UserRechargeRecordVo;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
public class UserRechargeRecordServiceImpl extends ServiceImpl<UserRechargeRecordMapper, UserRechargeRecord> implements UserRechargeRecordService {

    @Autowired
    UserRechargeRecordMapper userRechargeRecordMapper;

    @Override
    public List<UserRechargeRecordVo> searchUserRechargeRecord(String userNo){

        String lastTime = LocalDate.now().toString();

        String beforeTime = LocalDate.now().minusMonths(6).toString();

        List<Integer> integers = userRechargeRecordMapper.searchMouth(userNo,lastTime,beforeTime);

        List<UserRechargeRecordVo> list = new ArrayList<UserRechargeRecordVo>();

        for (int i = 0; i <integers.size(); i++) {
            int mouth = integers.get(i);
            UserRechargeRecordVo  userRechargeRecordVo = new UserRechargeRecordVo();
            List<UserRechargeRecord> records = userRechargeRecordMapper.searchUserRechargeRecord(userNo,mouth, lastTime,beforeTime);

            userRechargeRecordVo.setIntegers(mouth);
            userRechargeRecordVo.setRecords(records);
            list.add(userRechargeRecordVo);
        }
        return list;
    }
}
