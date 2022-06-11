package com.nbrt.kybao.controller;

import com.nbrt.kybao.entity.UserRechargeRecord;

import com.nbrt.kybao.service.UserRechargeRecordService;
import com.nbrt.kybao.utils.AjaxResponse;
import com.nbrt.kybao.utils.JwtHelper;
import com.nbrt.kybao.vo.UserRechargeRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户充值记录" )
@RestController
@CrossOrigin
@RequestMapping("/userRecharge")
public class UserRechargeRecordController {

    @Autowired
    UserRechargeRecordService userRechargeRecordService;


    @ApiOperation("移动端：查询用户充值")
    @PostMapping("/searchUserConsumption")
    public AjaxResponse<List<UserRechargeRecordVo>> searchUserRechargeRecord(@RequestParam String token){
        if(token == null) {
            return AjaxResponse.error("token字符串为空");
        }
        String userNo = JwtHelper.getUserName(token);
        return AjaxResponse.success(userRechargeRecordService.searchUserRechargeRecord(userNo));
    }



//    @ApiOperation("移动端：增加用户充值金额")
//    @PostMapping("/insertUserConsumption")
//    public AjaxResponse insertUserConsumption(String token, Integer id, ){
//        if(token == null) {
//            return AjaxResponse.error("token字符串为空");
//        }
//        String userNo = JwtHelper.getUserName(token);
//        List<UserRechargeRecord> user_no = userRechargeRecordMapper.selectList(new QueryWrapper<UserRechargeRecord>().eq("user_no", userNo));
//        UserRechargeRecord record = new UserRechargeRecord();
//        record.setId(id);
//        record.setRecharge_amount();
//        userRechargeRecordMapper.insert()
//        return AjaxResponse.success(userRechargeRecordMapper);
//    }


}
