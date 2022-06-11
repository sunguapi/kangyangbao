package com.nbrt.kybao.user.controller;

import com.nbrt.kybao.user.service.ReceiveService;
import com.nbrt.kybao.user.utils.R;
import com.nbrt.kybao.user.vo.UserReceiveVo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 收货信息表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-24 18:13:07
 */
@CrossOrigin
@Api(tags = "收货模块")
@RestController
@RequestMapping("homecare/receive")
public class ReceiveController {


    private final ReceiveService receiveService;

    public ReceiveController(ReceiveService receiveService) {
        this.receiveService = receiveService;
    }

    @ApiOperation("(移动端)添加收货地址")
    @PostMapping("/save/receive")
    public R receiveSave(@RequestBody UserReceiveVo userReceiveVo) {
        receiveService.saveReceive(userReceiveVo);
        return R.ok();
    }

    @ApiOperation("(移动端)收获地址列表")
    @GetMapping("/list/receive/")
    public R receiveList(@ApiParam(value = "token令牌", required = true)
                         @RequestParam("token") String token) {
        List<UserReceiveVo> userReceiveVoList = receiveService.getReceiveList(token);
        return R.ok().setData(userReceiveVoList);
    }

    @ApiOperation("(移动端)获取用户默认收货地址")
    @GetMapping("/user/receive/")
    public R getUserDefaultReceiveByUsername(@RequestParam("username") String username) {
        UserReceiveVo userReceiveVo = receiveService.getUserDefaultReceiveByUsername(username);
        return R.ok().setData(userReceiveVo);
    }

    @ApiOperation("(移动端)修改用户收货地址")
    @PostMapping("/user/receive/update")
    public R updateReceive(@RequestBody UserReceiveVo userReceiveVo) {
        receiveService.updateReceive(userReceiveVo);
        return R.ok();
    }

    @ApiOperation("(feign调用)根据收货编号获取地址信息")
    @GetMapping("/user/receive/info")
    public R getReceiveById(@RequestParam("receiveNo") String receiveId) {
        UserReceiveVo userReceiveVo = receiveService.getReceiveById(receiveId);
        return R.ok().setData(userReceiveVo);
    }
}
