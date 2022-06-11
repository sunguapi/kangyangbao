package com.nbrt.kybao.homecare.controller;

import com.nbrt.kybao.homecare.service.QuickOrderService;
import com.nbrt.kybao.homecare.utils.R;
import com.nbrt.kybao.homecare.vo.order.OrderConfirmVo;
import com.nbrt.kybao.homecare.vo.order.OrderQuickDataVo;
import com.nbrt.kybao.homecare.vo.order.OrderSubmitQuickVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 快捷订单表
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-06-05 13:00:35
 */
@CrossOrigin
@RestController
@Api(tags = "快捷订单模块")
@RequestMapping("user/homecarequickorder")
public class QuickOrderController {
    private final QuickOrderService homecareQuickOrderService;

    public QuickOrderController(QuickOrderService homecareQuickOrderService) {
        this.homecareQuickOrderService = homecareQuickOrderService;
    }

    /**
     * 快捷列表
     */
    @ApiOperation("快捷订单列表")
    @GetMapping("/list/quick/order")
    public R list(@RequestParam("token") String token){
        List<OrderQuickDataVo> quickDataVo = homecareQuickOrderService.getQuickOrderList(token);
        return R.ok().setData(quickDataVo);
    }

    @ApiOperation("快捷订单确认")
    @GetMapping("/quick/order/info/{id}")
    public R quickConfirm(@PathVariable String id) {
        OrderConfirmVo orderConfirmVo = homecareQuickOrderService.confirmOrder(id);
        return R.ok().setData(orderConfirmVo);
    }

    @ApiOperation("生成快捷订单表信息")
    @PostMapping("/save/quick/order")
    public R saveQuickOrder(@RequestBody OrderSubmitQuickVo orderSubmitQuickVo) {
        homecareQuickOrderService.submitQuickOrder(orderSubmitQuickVo);
        return R.ok();
    }

//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{id}")
//    public R info(@PathVariable("id") Integer id){
//		HomecareQuickOrderEntity homecareQuickOrder = homecareQuickOrderService.getById(id);
//
//        return R.ok().put("homecareQuickOrder", homecareQuickOrder);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    public R save(@RequestBody HomecareQuickOrderEntity homecareQuickOrder){
//		homecareQuickOrderService.save(homecareQuickOrder);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    public R update(@RequestBody HomecareQuickOrderEntity homecareQuickOrder){
//		homecareQuickOrderService.updateById(homecareQuickOrder);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Integer[] ids){
//		homecareQuickOrderService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

}
