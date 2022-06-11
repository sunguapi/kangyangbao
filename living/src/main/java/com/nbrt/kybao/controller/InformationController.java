package com.nbrt.kybao.controller;


import com.nbrt.kybao.config.JwtConfig;
import com.nbrt.kybao.entity.Information;
import com.nbrt.kybao.entity.ReceivingAddress;
import com.nbrt.kybao.service.InformationService;
import com.nbrt.kybao.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@Api(tags = "用户信息")
@RequestMapping("/information")
@CrossOrigin
public class InformationController extends JwtConfig {

    private Map<String,Object> map=new HashMap<>();

    private Timer timer=new Timer();


    @Autowired
    InformationService informationService;

    @ApiOperation("注册验证码")
    @GetMapping("/getCode")
    public Respones getPhone(String phone) {

             Integer code = ValidateCodeUtils.generateValidateCode(6);
             System.out.println("验证码："+code);
            try {
                boolean isok=SMSUtils.sendShortMessage(SMSUtils.ORDER_LOGIN, phone, code.toString());
                if (isok){
                    map.put(phone,code);

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                             map.remove(phone);
                        }
                    }, 1000 * 60);

                    return new Respones(200, "success", "发送成功");
                }else {
                    return new Respones(300, "failure", "发送失败~");
                }


            } catch (Exception e) {
                // 验证码发送失败
                e.printStackTrace();
                return new Respones(300, "failure", "发送失败~");
            }
    }


    @ApiOperation("登陆账号")
    @PostMapping("/login")
    public Respones register(@RequestBody Map<String,Object> maps) {
            try {
                String phone= (String) maps.get("phone");
                String code = (String) maps.get("code");

                if (map.get(phone).toString().equals(code)) {

                    if (phone.equals(informationService.getPhone(phone))) {
                        Map<String, String> map = new HashMap<>();
                        map.put("phone", phone);
                        String token = JWTUtil.getToken(map);

                        return new Respones(200, "success", token);
                    }
                    Information information = new Information();
                    information.setUsername(phone);
                    information.setAge("0岁");
                    information.setAvatar("");
                    information.setNick(RandomStringUtil.getRandomString(6));
                    information.setSex("男");
                    informationService.register(information);

                    Map<String, String> map = new HashMap<>();
                    map.put("phone", phone);
                    String token = JWTUtil.getToken(map);

                    return new Respones(200, "success", token);
                } else {
                    return new Respones(300, "failure", "验证码不正确");
                }

            } catch (Exception e) {
                return new Respones(300, "failure", "验证码不正确");
            }

    }


    @ApiOperation("获取账号信息")
    @GetMapping("/getToken")
    public Respones getToken(HttpServletRequest httpServletRequest) throws Exception {

       String token=httpServletRequest.getHeader("token");

       String phone=JWTUtil.getTokenInfo(token).get("phone");

        return new Respones(200, "请求成功", informationService.getInformation(phone));
    }


    @ApiImplicitParam(name = "map",value="{'phone':'123' , 'pwd':'123'}")
    @ApiOperation("修改密码")
    @PostMapping("/setPwd")
    public Respones setPwd(@RequestBody Map<String,Object> map){

      int index=informationService.setPwd(map.get("phone").toString(),map.get("pwd").toString());

      if (index==0){
          return new Respones(200, "failure", "修改失败");
      }
        return new Respones(200, "success", "修改成功");
    }


    @ApiOperation("新增收货地址")
    @PostMapping("/Address")
    public Respones receivingAddress(@RequestBody ReceivingAddress address){

        if (informationService.receivingAddress(address)){
            return new Respones(200, "success", "添加成功");

        }
        return new Respones(200, "failure", "添加失败");
    }





}
