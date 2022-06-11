package com.nbrt.kybao.mall.controller;

import com.nbrt.kybao.mall.dto.common.ApiResult;
import com.nbrt.kybao.mall.service.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjh
 * @date 2022/5/24 11:25
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
@Api(tags = "用户控制")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户名登录")
    @PostMapping("/username/login")
    public ApiResult<String> usernameLogin(){
        return null;
    }
}
