package com.nbrt.kybao.user.controller;

import java.util.Map;

import com.nbrt.kybao.user.constant.UserEnum;
import com.nbrt.kybao.user.utils.R;
import com.nbrt.kybao.user.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.user.service.AdminInfoService;




/**
 * 
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-25 11:14:26
 */
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("homecare/admininfo")
@Api(tags = "后台用户模块")
public class AdminInfoController {
    private final AdminInfoService adminInfoService;

    public AdminInfoController(AdminInfoService adminInfoService) {
        this.adminInfoService = adminInfoService;
    }

    /**
     * 登录
     * @param userLoginVo 登录封装的数据
     * @return 返回登录成功封装的数据
     */
    @ApiOperation("(管理端)用户登录")
    @PostMapping("/login")
    public R login(@RequestBody UserLoginVo userLoginVo) {

        Map<Object,Object> loginResult = adminInfoService.login(userLoginVo);
        String msg = (String) loginResult.get("msg");
        if(msg.equals(UserEnum.USER_NOT_EXIST.getMsg())) {
            return R.error(UserEnum.USER_NOT_EXIST.getCode(), UserEnum.USER_NOT_EXIST.getMsg());
        }else if(msg.equals(UserEnum.PASSWORD_ERROR.getMsg())) {
            return R.error(UserEnum.PASSWORD_ERROR.getCode(), UserEnum.PASSWORD_ERROR.getMsg());
        } else {
            loginResult.remove("msg");
            return R.ok().put("data",loginResult);
        }
    }

}
