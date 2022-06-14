package com.nbrt.kybao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.User;
import com.nbrt.kybao.entity.UserRechargeRecord;
import com.nbrt.kybao.entity.VipType;
import com.nbrt.kybao.service.ChildrenService;
import com.nbrt.kybao.service.UserRechargeRecordService;
import com.nbrt.kybao.service.UserService;
import com.nbrt.kybao.service.VipTypeServie;
import com.nbrt.kybao.utils.AjaxResponse;
import com.nbrt.kybao.utils.JwtHelper;
import com.nbrt.kybao.utils.MyPageUtils;
import com.nbrt.kybao.vo.UserChildrenVo;
import com.nbrt.kybao.vo.UserInfoVo;
import com.nbrt.kybao.vo.UserVo;
import io.jsonwebtoken.Jwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Api(tags = "用户中心" )
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private VipTypeServie vipTypeServie;

    @Autowired
    private ChildrenService childrenService;

    @Autowired
    private UserRechargeRecordService userRechargeRecordService;

    @ApiOperation("用户中心：修改密码")
    @PostMapping("/updateUserPassWord")
    public AjaxResponse updateUserPassWord(String token, String oldPassword, String newPassword) {
        String UserNO = JwtHelper.getUserName(token);
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("user_no", UserNO).eq("user_password", oldPassword);
        User user = userService.getBaseMapper().selectOne(wrapper);
        if(user == null) {
            return AjaxResponse.error("密码验证失败，请重新输入");
        }
        user.setUserPassword(newPassword);
        userService.getBaseMapper().updateById(user);
        return AjaxResponse.success();

    }

    @ApiOperation("用户中心：个人信息")
    @PostMapping("/updateUserInfo")
    public AjaxResponse updateUserInfo(@RequestBody User user) {
        userService.getBaseMapper().updateById(user);
        return AjaxResponse.success();
    }


    /**
     * 通过用户实名查询用户信息
     * @return
     */
    @ApiOperation("用户中心：通过名称或者昵称搜索")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "userName", value = "用户实名"),
            @ApiImplicitParam(name = "nickName", value = "用户昵称")}
    )
    @GetMapping("/searchUserByName")
    public AjaxResponse<Page<UserVo>> searchUserByName(String userName, String nickName, int pageNumber, int pageSize) {
        Page<UserVo> page = userService.searchUserByName(userName, nickName, pageNumber, pageSize);
        return AjaxResponse.success(page);
    }


    @ApiOperation("用户中心：会员等级")
    @GetMapping("/selectVipType")
    public AjaxResponse<List<VipType>> selectVipType() {
        return AjaxResponse.success(vipTypeServie.selectVipType());
    }

    /**
     *
     * @param id
     * @param
     * @return
     */
    @ApiOperation("用户中心：修改会员等级")
    @GetMapping("/setVipType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vipId", value = "必须是已有的会员类型，"),
            @ApiImplicitParam(name = "id", value = "用户id")
    })
    public AjaxResponse setVipType(int id, int vipId) {
        if(selectVipType(vipId) != null) {
            userService.setVipType(id, vipId);
        }
        return AjaxResponse.success();
    }


    /**
     * 查询用户详情
     * @return
     */
    @ApiOperation("用户中心：查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id"),
    })
    @GetMapping("/searchUserInfo")
    public AjaxResponse<UserInfoVo> searchUserInfoById(int id) {
            return AjaxResponse.success(userService.searchUserInfoById(id));
    }


    /**
     * 会员卡
     */
    @ApiOperation("会员中心：查询所有会员卡")
    @GetMapping("/vipCard")
    public AjaxResponse<Page<VipType>> searchVipCard(int pageNum, int pageSize) {
        Page<VipType> page=new Page<>(pageNum,pageSize);
        Page<VipType> pages=vipTypeServie.getPageList(page);
        return AjaxResponse.success(pages);
    }

    @ApiOperation("会员中心：添加会员卡")
    @PostMapping("/insertCard")
    public AjaxResponse insertVipCard(@RequestBody VipType vipTypeInfo) {
        vipTypeServie.getBaseMapper().insert(vipTypeInfo);
        return AjaxResponse.success();
    }

    @ApiOperation("会员中心：点击会员卡进入修改页面")
    @GetMapping("/updateVipCard/{id}")
    public AjaxResponse<VipType> updateVipCard(@PathVariable int id) {
        return AjaxResponse.success(vipTypeServie.getBaseMapper().selectById(id));
    }

    @ApiOperation("会员中心：修改后提交")
    @PostMapping("/updateAfterInsertVipCard")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deAddress",value = "图片地址")
    })
    public AjaxResponse updateAfterInsertVipCard( @RequestBody VipType vipType) {
        return AjaxResponse.success(vipTypeServie.getBaseMapper().updateById(vipType));
    }

    /**
     *子女
     */
    @ApiOperation("子女：通过用户id查询到子女的信息列表")
    @GetMapping("/searchChildrenById/{primaryUserNo}")
    public AjaxResponse<Page<UserChildrenVo>> searchChildrenById(
            @PathVariable("primaryUserNo") String primaryUserNo,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ) {

        return AjaxResponse.success(childrenService.searchChildrenById(primaryUserNo, pageNum, pageSize));
    }


    private VipType selectVipType(int vipId) {
        return vipTypeServie.getBaseMapper().selectById(vipId);
    }


    @ApiOperation("移动端：查询用户积分")
    @PostMapping("/searchUserConsumption")
    public AjaxResponse searchUserConsumption(String token){
        if(token == null) {
            return AjaxResponse.error("token字符串为空");
        }
        return AjaxResponse.success(userService.searchUserConsumption(token));
    }

    @ApiOperation("移动端：充值金额")
    @PostMapping("/insertUserBalance")
    public AjaxResponse insertUserBalance(String token, BigDecimal money){
        if(token == null) {
            return AjaxResponse.error("token字符串为空");
        }
        String userNo = JwtHelper.getUserName(token);
        Calendar instance = Calendar.getInstance();
        UserRechargeRecord record = new UserRechargeRecord();
        record.setUserNo(userNo);
        record.setRechargeAmount(money);
        record.setMouth(instance.get(Calendar.MONTH) + 1);
        userRechargeRecordService.save(record);
        User one = userService.getOne(new QueryWrapper<User>().eq("user_no", userNo));
        UpdateWrapper<User> set = new UpdateWrapper<User>().eq("user_no", userNo).set("balance", one.getBalance().add(money));
        userService.update(set);
        return AjaxResponse.success();
    }


    @ApiOperation("移动端：查询金额")
    @PostMapping("/searchUserBalance")
    public AjaxResponse searchUserBalance(String token){
        if(token == null) {
            return AjaxResponse.error("token字符串为空");
        }
        String userNo = JwtHelper.getUserName(token);
        return AjaxResponse.success(userService.getOne(new QueryWrapper<User>().eq("user_no", userNo)).getBalance());
    }

    @ApiOperation("移动端：修改昵称")
    @PostMapping("/updateNickName")
    public AjaxResponse updateNickName(String token,String nickName){
        if(token == null) {
            return AjaxResponse.error("token字符串为空");
        }
        String userNo = JwtHelper.getUserName(token);
        userService.update(new UpdateWrapper<User>().eq("user_no", userNo).set("nick_name", nickName));
        return AjaxResponse.success();
    }

    @ApiOperation("移动端：修改头像")
    @PostMapping("/updateUserAvatar")
    public AjaxResponse updateUserAvatar(String token,String userAvatar){
        if(token == null) {
            return AjaxResponse.error("token字符串为空");
        }
        String userNo = JwtHelper.getUserName(token);
        userService.update(new UpdateWrapper<User>().eq("user_no", userNo).set("user_avatar", userAvatar));
        return AjaxResponse.success();
    }
}
