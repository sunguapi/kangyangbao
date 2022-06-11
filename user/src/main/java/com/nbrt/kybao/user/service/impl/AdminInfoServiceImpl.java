package com.nbrt.kybao.user.service.impl;

import com.nbrt.kybao.user.component.OSSComponent;
import com.nbrt.kybao.user.constant.UserEnum;
import com.nbrt.kybao.user.utils.JwtHelper;
import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.utils.Query;
import com.nbrt.kybao.user.vo.UserLoginVo;
import com.nbrt.kybao.user.dao.AdminInfoDao;
import com.nbrt.kybao.user.entity.AdminInfoEntity;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.nbrt.kybao.user.service.AdminInfoService;
import org.springframework.util.StringUtils;


@Service("adminInfoService")
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoDao, AdminInfoEntity> implements AdminInfoService {

    private final StringRedisTemplate stringRedisTemplate;

    private final OSSComponent ossComponent;

    public AdminInfoServiceImpl(StringRedisTemplate stringRedisTemplate, OSSComponent ossComponent) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.ossComponent = ossComponent;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminInfoEntity> page = this.page(
                new Query<AdminInfoEntity>().getPage(params),
                new QueryWrapper<AdminInfoEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 登录
     * @param userLoginVo 登录封装的数据
     * @return 返回登录成功封装的数据
     */
    @Override
    public Map<Object, Object> login(UserLoginVo userLoginVo) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        //1.根据userId查询用户信息
        QueryWrapper<AdminInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userLoginVo.getUsername());
        AdminInfoEntity adminInfoEntity = this.getOne(wrapper);
        System.out.println(adminInfoEntity + "adminInfoEntity");
        //用户不存在
        if(StringUtils.isEmpty(adminInfoEntity)) {
            hashMap.put("code", UserEnum.USER_NOT_EXIST.getCode());
            hashMap.put("msg", UserEnum.USER_NOT_EXIST.getMsg());
            return hashMap;
        }
        if(!userLoginVo.getPassword().equals(adminInfoEntity.getPassword())) {
            hashMap.put("code", UserEnum.PASSWORD_ERROR.getCode());
            hashMap.put("msg", UserEnum.PASSWORD_ERROR.getMsg());
            return hashMap;
        }


        //TODO 短信验证码验证
        //2.生成token
        String token = JwtHelper.createToken(userLoginVo.getUsername());
        hashMap.put("msg", UserEnum.LOGIN_SUCCESS.getMsg());
        hashMap.put("token",token);
        return hashMap;
    }

}