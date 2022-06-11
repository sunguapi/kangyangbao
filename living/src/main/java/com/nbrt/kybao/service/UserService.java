package com.nbrt.kybao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.entity.User;
import com.nbrt.kybao.entity.UserRechargeRecord;
import com.nbrt.kybao.utils.MyPageUtils;
import com.nbrt.kybao.vo.UserInfoVo;
import com.nbrt.kybao.vo.UserVo;

import java.math.BigDecimal;


public interface UserService extends IService<User> {

    /**
     * 通过用户实名查询用户
     * @param userName
     * @return
     */
    Page<UserVo> searchUserByName(String userName, String nickName, Integer pageNumber, Integer pageSize);

    void setVipType(Integer id, int vipId);

    UserInfoVo searchUserInfoById(Integer id);

    int searchUserConsumption(String token);



//    void updateUserConsumption(String token, BigDecimal consumptionAmount,BigDecimal DeductionOfIntegral);

}
