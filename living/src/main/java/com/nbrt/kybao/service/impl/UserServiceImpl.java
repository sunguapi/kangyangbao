package com.nbrt.kybao.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.entity.User;
import com.nbrt.kybao.entity.UserRechargeRecord;
import com.nbrt.kybao.entity.VipType;
import com.nbrt.kybao.mapper.UserMapper;
import com.nbrt.kybao.mapper.VipTypeMapper;
import com.nbrt.kybao.service.UserService;
import com.nbrt.kybao.utils.JwtHelper;
import com.nbrt.kybao.utils.MyPageUtils;
import com.nbrt.kybao.vo.UserInfoVo;
import com.nbrt.kybao.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User>implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private VipTypeMapper vipTypeMapper;


    /**
     * 通过用户实名查询用户信息
     * @param userName
     * @return
     */
    @Override
    public Page<UserVo> searchUserByName(String userName, String nickName, Integer pageNumber, Integer pageSize) {

        Page<UserVo> page=new Page<>(pageNumber, pageSize);
        Page<UserVo> pages=userMapper.selectPageList(page,userName,nickName);
        return pages;
    }

    @Override
    public void setVipType(Integer id, int vipId) {
        User user = new User();
        user.setId(id);
        user.setUserGrade(vipId);
        userMapper.updateById(user);
    }

    @Override
    public UserInfoVo searchUserInfoById(Integer id) {

        User user = userMapper.selectById(id);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setVipType(vipTypeMapper.selectById(user.getUserGrade()).getVipType());
        BeanUtils.copyProperties(user, userInfoVo);
        return userInfoVo;
    }

    @Override
    public int searchUserConsumption(String token) {

        String UserNo = JwtHelper.getUserName(token);
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserNo, UserNo));
        return user.getUserIntegral();
    }



//    @Override
//    public void updateUserConsumption(String token, BigDecimal consumptionAmount,BigDecimal DeductionOfIntegral) {
//        String UserNo = JwtHelper.getUserName(token);
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUserNo, UserNo);
//        User user = new User();
//        user.setUser()
//        userMapper.update()
//    }


}
