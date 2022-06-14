package com.nbrt.kybao.user.controller;


import com.nbrt.kybao.user.constant.UserEnum;
import com.nbrt.kybao.user.entity.UserEntity;
import com.nbrt.kybao.user.service.UserService;
import com.nbrt.kybao.user.utils.JwtHelper;
import com.nbrt.kybao.user.utils.R;
import com.nbrt.kybao.user.vo.user.UserLoginRespVo;
import com.nbrt.kybao.user.vo.UserLoginVo;
import com.nbrt.kybao.user.vo.user.UserRegistRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 用户表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 18:13:07
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/user")
@Api(tags = "前台用户模块")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/user/info/")
    public R getUserInfo(@RequestParam("username") String username) {
        UserEntity userEntity = userService.getUserInfoByUsername(username);
        return R.ok().setData(userEntity);
    }

    /**
     * 短信验证码发送
     * @param phoneNumber 手机号码
     */
    @ApiOperation("(前台)短信验证码发送")
    @PostMapping("/code/{phoneNumber}")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "phoneNumber", value = "手机号码")
    )
    public R sendCode(@PathVariable String phoneNumber) {
        try {
            userService.sendCode(phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    /**
     * 用户手机号登录
     * @param code 验证码
     * @return 返回用户一些响应信息
     */
    @ApiOperation("(前台)用户手机号登录")
    @PostMapping("/phone/login/{code}/{phoneNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "验证码"),
            @ApiImplicitParam(name = "phoneNum", value = "手机号")
    })
    public R phoneLogin(@PathVariable("code") String code,
                        @PathVariable("phoneNum") String phoneNum) {
        UserLoginRespVo userLoginRespVo = userService.phoneLogin(code,phoneNum);
        if(userLoginRespVo == null) {
            return R.error(UserEnum.CODE_ERROR.getCode(),UserEnum.CODE_ERROR.getMsg());
        }
        return R.ok().put("data",userLoginRespVo);
    }

    @PostMapping("/user/login")
    @ApiOperation("(前台)用户账号密码登录")
    public R usernameAndPwdLogin(@RequestBody UserLoginVo userLoginVo) {
        UserLoginRespVo userLoginRespVo = userService.usernameAndPwdLogin(userLoginVo);
        if(userLoginRespVo.getCode() != null) {
            if(userLoginRespVo.getCode().equals(UserEnum.USER_NOT_EXIST.getCode())) {
                return R.error(UserEnum.USER_NOT_EXIST.getCode(),UserEnum.USER_NOT_EXIST.getMsg());
            } else if(userLoginRespVo.getCode().equals(UserEnum.PASSWORD_ERROR.getCode())) {
                return R.error(UserEnum.PASSWORD_ERROR.getCode(),UserEnum.PASSWORD_ERROR.getMsg());
            }
        }
        return R.ok().put("data",userLoginRespVo);
    }

    @PostMapping("/user/regist")
    @ApiOperation("(前台)用户注册")
    public R userRegist(@RequestBody UserLoginVo userLoginVo) {
        UserRegistRespVo registVo = userService.regist(userLoginVo);
        if(registVo.getCode().equals(UserEnum.USER_EXISTED.getCode())) {
            //用户已存在
            return R.error(UserEnum.USER_EXISTED.getCode(),UserEnum.USER_EXISTED.getMsg());
        }
        //注册成功
        return R.ok(UserEnum.USER_REGIST_SUCCESS.getMsg());
    }

//    @PostMapping("/user/orders")
//    @ApiOperation("移动端：我的订单")
//    public R userOrders(@RequestHeader(value = "token") String token, @RequestParam Map<String ,Object> params) {
//        if(token == null)
//        {
//            return R.error("token为空");
//        }
//        String userNO = JwtHelper.getUserName(token);
//
////        return R.ok().;
//        return null;
//    }

}
