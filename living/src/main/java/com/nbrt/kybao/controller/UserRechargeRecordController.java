package com.nbrt.kybao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nbrt.kybao.entity.UserRechargeRecord;
import com.nbrt.kybao.mapper.UserRechargeRecordMapper;
import com.nbrt.kybao.utils.AjaxResponse;
import com.nbrt.kybao.utils.JwtHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户充值记录" )
@RestController
@CrossOrigin
@RequestMapping("/userRecharge")
public class UserRechargeRecordController {

    @Autowired
    UserRechargeRecordMapper userRechargeRecordMapper;


    @ApiOperation("移动端：查询用户充值金额")
    @PostMapping("/searchUserConsumption")
    public AjaxResponse searchUserRechargeRecord(String token){
        if(token == null) {
            return AjaxResponse.error("token字符串为空");
        }
        String userNo = JwtHelper.getUserName(token);
        return AjaxResponse.success(userRechargeRecordMapper.selectList(new QueryWrapper<UserRechargeRecord>().eq("user_no", userNo)));
    }




//    @ApiOperation("移动端：增加用户充值金额")
//    @PostMapping("/insertUserConsumption")
//    public AjaxResponse insertUserConsumption(String token){
//        if(token == null) {
//            return AjaxResponse.error("token字符串为空");
//        }
//        String userNo = JwtHelper.getUserName(token);
//        UserRechargeRecord record = new UserRechargeRecord();
//
//        userRechargeRecordMapper.insert()
//        return AjaxResponse.success(userRechargeRecordMapper.i(new QueryWrapper<UserRechargeRecord>().eq("user_no", userNo)));
//    }
}
