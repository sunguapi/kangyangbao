package com.nbrt.kybao.homecare.feign;

import com.nbrt.kybao.homecare.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user", url = "http://101.132.172.154:11000")
public interface UserFeignService {

    //获取默认收货地址
    @GetMapping("/homecare/receive/user/receive/")
    R getUserDefaultReceiveByUsername(@RequestParam("username") String username);

    //获取用户信息
    @GetMapping("/homecare/user/user/info/")
    R getUserInfo(@RequestParam("username") String username);

    @GetMapping("/homecare/receive/user/receive/info")
    R getReceiveById(@RequestParam("receiveNo") String receiveId);

}
