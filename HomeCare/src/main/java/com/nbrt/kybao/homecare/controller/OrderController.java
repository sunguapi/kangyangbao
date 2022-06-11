package com.nbrt.kybao.homecare.controller;

import java.util.Map;

import com.nbrt.kybao.homecare.utils.MyPageUtils;

import com.nbrt.kybao.homecare.utils.R;

import com.nbrt.kybao.homecare.vo.order.*;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.homecare.service.OrderService;




/**
 * 订单表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-30 09:37:51
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/homecareorder")
@Api(tags = "订单模块")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation("(移动端)确认订单")
    @PostMapping("/confirm/order/{token}/{serverItemId}")
    public R confirmOrder(
            @ApiParam(value = "token令牌", required = true) @PathVariable String token,
            @ApiParam(value = "服务项id", required = true) @PathVariable Long serverItemId) {
        if(token == null) {
            return R.error("token 为空");
        }
        OrderConfirmVo orderConfirmVo = orderService.confirmOrder(token,serverItemId);

        return R.ok().put("data",orderConfirmVo);
    }

    @ApiOperation("(管理端)订单详情")
    @GetMapping("/order/detail/admin/{orderNo}")
    public R getOrderDetail(@PathVariable("orderNo") String orderNo) {
        OrderAdminDetailVo orderAdminDetailVo = orderService.getAdminOrderDetail(orderNo);
        return R.ok().setData(orderAdminDetailVo);
    }

    @ApiOperation("(管理端)获取订单列表")
    @GetMapping("/list/order")
    public R getOrderList(@RequestParam Map<String, Object> params) {
        MyPageUtils<OrderListVo> page = orderService.queryPage(params);
        return R.ok().put("data",page);
    }

    @ApiOperation("(移动端)订单详情")
    @GetMapping("/detail/order/{token}")
    public R getDetailOrder(@PathVariable String token) {
        OrderItemDetail orderItemDetail = orderService.getOrderDetail(token);
        return R.ok().setData(orderItemDetail);
    }

    @ApiOperation("(移动端)订单提交")
    @PostMapping("/submit/order")
    public R submitOrder(@RequestBody OrderSubmitVo orderSubmitVo) {
        orderService.submitOrder(orderSubmitVo);
        return R.ok();
    }

    @ApiOperation("(管理端)退款详情")
    @GetMapping("/refound/order/admin")
    public R getRefoundDetail(@PathVariable("orderNo") String orderNo) {
        OrderAdminRefoundVo orderAdminRefoundVo = orderService.getRefoundDetail(orderNo);
        return R.ok().setData(orderAdminRefoundVo);
    }

    @ApiOperation("(移动端)取消订单")
    @GetMapping("/cancel/order/{orderNo}")
    public R cancelOrder(@PathVariable String orderNo) {
        orderService.cancelOrder(orderNo);
        return R.ok();
    }

    @ApiOperation("(移动端)修改地址")
    @PostMapping("/update/receive")
    public R updateReceive(@RequestParam("receiveNo") String receiveNo) {
        return R.ok();
    }

}
