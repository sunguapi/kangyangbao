package com.nbrt.kybao.user.service.impl;

import com.nbrt.kybao.user.component.SMSComponent;
import com.nbrt.kybao.user.constant.UserEnum;
import com.nbrt.kybao.user.utils.JwtHelper;
import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.utils.Query;
import com.nbrt.kybao.user.dao.UserDao;
import com.nbrt.kybao.user.entity.UserEntity;
import com.nbrt.kybao.user.service.UserService;
import com.nbrt.kybao.user.vo.user.UserLoginRespVo;
import com.nbrt.kybao.user.vo.UserLoginVo;
import com.nbrt.kybao.user.vo.user.UserRegistRespVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    private final StringRedisTemplate stringRedisTemplate;
    private final SMSComponent smsComponent;
    private final UserDao userDao;

    public UserServiceImpl(StringRedisTemplate stringRedisTemplate, SMSComponent smsComponent, UserDao userDao) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.smsComponent = smsComponent;
        this.userDao = userDao;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    /**
     * 短信验证码发送
     * @param phoneNumber 需要发送验证码的手机号
     */
    @Override
    public void sendCode(String phoneNumber) throws Exception {
        //先查看redis中是否有这个手机号发送的验证码
        String code;
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String redisCode = stringStringValueOperations.get("code" + phoneNumber);
        //有该验证码则直接返回
        if(redisCode != null) {
            return;
        }
        //没有则生成验证码并发送
        code = UUID.randomUUID().toString().substring(0,6).replaceAll("-","");
        smsComponent.sendCode(phoneNumber,code);
        stringStringValueOperations.set("code" + phoneNumber,code,60L, TimeUnit.SECONDS);
    }

    /**
     * 用户手机号登录
     * @param code 验证码
     * @return 返回用户信息响应数据
     */
    @Override
    public UserLoginRespVo phoneLogin(String code,String phoneNum) {
        UserLoginRespVo userLoginRespVo = new UserLoginRespVo();
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String redisCode = stringStringValueOperations.get("code" + phoneNum);
        if(!redisCode.equals(code)) {
            return null;
        }
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone",phoneNum);
        UserEntity userEntity = this.getOne(queryWrapper);
        if(userEntity != null) {
            userLoginRespVo.setAvatar(userEntity.getUserAvatar());
            userLoginRespVo.setUserGender(userEntity.getUserGender());
            userLoginRespVo.setUserPhone(userEntity.getUserPhone());
            userLoginRespVo.setUserPassword(userEntity.getUserPassword());
            userLoginRespVo.setFaceCapture(userEntity.getFaceCapture());
            userLoginRespVo.setNikeName(userEntity.getNickName());
            userLoginRespVo.setWeChatBinding(userEntity.getWechatBinding());
            String token = JwtHelper.createToken(userEntity.getUserNo());
            userLoginRespVo.setToken(token);
        }
        return userLoginRespVo;
    }

    /**
     * 用户账号密码登录
     * @param userLoginVo 用户登录信息封装
     * @return 用户信息响应数据
     */
    @Override
    public UserLoginRespVo usernameAndPwdLogin(UserLoginVo userLoginVo) {
        UserLoginRespVo userLoginRespVo = new UserLoginRespVo();

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_no",userLoginVo.getUsername());
        UserEntity userEntity = this.getOne(queryWrapper);
        //用户存在但是密码错误直接返回
        if(userEntity != null && !userLoginVo.getPassword().equals(userEntity.getUserPassword())) {
            userLoginRespVo.setCode(UserEnum.PASSWORD_ERROR.getCode());
            return userLoginRespVo;
        }
        //用户存在则返回用户基本信息并返回token令牌
        if(userEntity != null) {
            userLoginRespVo.setAvatar(userEntity.getUserAvatar());
            userLoginRespVo.setUserGender(userEntity.getUserGender());
            userLoginRespVo.setUserPhone(userEntity.getUserPhone());
            userLoginRespVo.setUserPassword(userEntity.getUserPassword());
            userLoginRespVo.setFaceCapture(userEntity.getFaceCapture());
            userLoginRespVo.setNikeName(userEntity.getNickName());
            userLoginRespVo.setWeChatBinding(userEntity.getWechatBinding());
            String token = JwtHelper.createToken(userEntity.getUserNo());
            userLoginRespVo.setToken(token);
            //用户不存在则直接返回
        } else {
            userLoginRespVo.setCode(UserEnum.USER_NOT_EXIST.getCode());
        }
        return userLoginRespVo;
    }

    /**
     * 获取用户信息
     * @param username 用户信息
     */
    @Override
    public UserEntity getUserInfoByUsername(String username) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_no",username);
        UserEntity userEntity = this.getOne(wrapper);
        System.out.println("sss==>" + userEntity);
        System.out.println("===>" + userEntity.getUserGrade());
        return userEntity;
    }

    /**
     * 用户注册
     * @param userLoginVo 用户封装数据
     */
    @Override
    public UserRegistRespVo regist(UserLoginVo userLoginVo) {
        UserRegistRespVo registVo = new UserRegistRespVo();

        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        String userNo = userLoginVo.getUsername();
        wrapper.eq("user_no",userNo);
        UserEntity userEntity = userDao.selectOne(wrapper);
        //用户已存在
        if(userEntity != null) {
            registVo.setCode(UserEnum.USER_EXISTED.getCode());
            return registVo;
        }

        //不存在则保存用户
        UserEntity user = new UserEntity();
        user.setUserNo(userLoginVo.getUsername());
        user.setUserPassword(userLoginVo.getPassword());
        user.setNickName(userLoginVo.getUsername());
        user.setUserGrade(1L);
        user.setUserIntegral(100);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        this.save(user);

        //返回token
        String token = JwtHelper.createToken(user.getNickName());
        registVo.setToken(token);
        registVo.setCode(UserEnum.USER_REGIST_SUCCESS.getCode());
        return registVo;
    }
}