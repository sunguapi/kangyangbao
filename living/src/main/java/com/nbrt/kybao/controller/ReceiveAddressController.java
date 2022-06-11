package com.nbrt.kybao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.nbrt.kybao.entity.ReceiveAddress;
import com.nbrt.kybao.entity.dto.ReceiveAddressDTO;
import com.nbrt.kybao.mapper.ReceiveAddressDTOMapper;
import com.nbrt.kybao.mapper.ReceiveAddressMapper;
import com.nbrt.kybao.utils.AjaxResponse;
import com.nbrt.kybao.utils.JwtHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@CrossOrigin
@Api(tags = "收货地址" )
@RestController
@RequestMapping("/receive")
@Accessors(chain = true)
public class ReceiveAddressController {

    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;
    @Autowired
    private ReceiveAddressDTOMapper receiveAddressDTOMapper;

    @ApiOperation("收货地址：增加用户快递信息")
    @PostMapping("/insertReceiveAddress")
    public AjaxResponse insertReceiveAddress(@RequestBody(required = false) ReceiveAddressDTO receiveAddressDTO) {
        System.out.println("==================>" + receiveAddressDTO);
        if(receiveAddressDTO == null) {
            return AjaxResponse.error("参数为空");
        }

//        ReceiveAddressVo 前端把token赋值到userNO中，后端解析转换拿到userNO
        String userNO = JwtHelper.getUserName(receiveAddressDTO.getUserNo());
        receiveAddressDTO.setUserNo(userNO);
        if(userNO == null) {
            return AjaxResponse.error("token有误");
        }
        if(receiveAddressDTO.getDefaultOn() == 1) {
            receiveAddressMapper.updateDefaultOn(receiveAddressDTO.getUserNo());
        }
        receiveAddressDTO.setReceiveNo(System.currentTimeMillis() + "");

        ReceiveAddress receiveAddress = new ReceiveAddress();
        BeanUtils.copyProperties(receiveAddressDTO,receiveAddress);

        receiveAddressMapper.insert(receiveAddress);
        return AjaxResponse.success();
    }



    @ApiOperation("收货地址：删除用户快递信息")
    @GetMapping("/deleteReceiveAddress")
    public AjaxResponse deleteReceiveAddress(String receiveNo) {
        QueryWrapper<ReceiveAddress> wrapper = new QueryWrapper<ReceiveAddress>().eq("receive_no", receiveNo);
        ReceiveAddress receiveAddress = new ReceiveAddress();
        receiveAddress.setDeleted(1);
        receiveAddressMapper.update(receiveAddress,wrapper);
        return AjaxResponse.success();
    }

    @ApiOperation("收货地址：修改用户快递信息")
    @PostMapping("/updateReceiveAddress")
    public AjaxResponse updateReceiveAddress(@RequestBody ReceiveAddress receiveAddress) {
        //        ReceiveAddressVo 前端把token赋值到userNO中，后端解析转换拿到userNO
        String userNO = JwtHelper.getUserName(receiveAddress.getUserNo());
        if(receiveAddress.getDefaultOn() == 1) {
            receiveAddressMapper.updateDefaultOn(receiveAddress.getUserNo());
        }
        receiveAddress.setUserNo(userNO);
        receiveAddressMapper.updateById(receiveAddress);
        return AjaxResponse.success();
    }

    @ApiOperation("收货地址：查询用户快递信息")
    @PostMapping("/searchReceiveAddress")
    public AjaxResponse searchReceiveAddress(@RequestBody String token) {

        System.out.println("========================================>" + token);

        if(null == token) {
            return AjaxResponse.error("token值为空");
        }
        String userNO = JwtHelper.getUserName(token);
        if(null == userNO) {
            return AjaxResponse.error("没有对应的用户信息");
        }
        QueryWrapper<ReceiveAddress> wrapper = new QueryWrapper<ReceiveAddress>().eq("user_no", userNO).eq("deleted",0);

        return AjaxResponse.success(receiveAddressMapper.selectList(wrapper));
    }

    @ApiOperation("收货地址：查询用户快递信息通过收货信息编号")
    @GetMapping("/searchReceiveAddressByReceiveNo")
    public AjaxResponse searchReceiveAddressByReceiveNo(String receiveNo) {
        if(null == receiveNo) {
            return AjaxResponse.error("收货信息编号为空");
        }
        QueryWrapper<ReceiveAddress> wrapper = new QueryWrapper<ReceiveAddress>().eq("receive_no", receiveNo).eq("deleted",0);

        return AjaxResponse.success(receiveAddressMapper.selectList(wrapper));
    }
}
