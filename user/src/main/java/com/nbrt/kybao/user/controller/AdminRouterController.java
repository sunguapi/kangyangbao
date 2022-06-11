package com.nbrt.kybao.user.controller;

import com.nbrt.kybao.user.utils.R;
import com.nbrt.kybao.user.vo.AdminMenuAndInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.user.service.AdminRouterService;



/**
 * 
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-25 11:14:26
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/adminrouter")
@Api(tags = "后台路由")
public class AdminRouterController {

    private final AdminRouterService adminRouterService;

    public AdminRouterController(AdminRouterService adminRouterService) {
        this.adminRouterService = adminRouterService;
    }

    @ApiOperation("(管理端)获取路由信息")
    @GetMapping("/routerInfo")
    public R getRouterInfo(@RequestParam("token") String token) {
        AdminMenuAndInfoVo adminMenuAndInfoVo = adminRouterService.getRouterInfo(token);
        return R.ok().put("data",adminMenuAndInfoVo);
    }
}
