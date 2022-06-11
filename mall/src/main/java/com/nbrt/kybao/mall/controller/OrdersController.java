package com.nbrt.kybao.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nbrt.kybao.mall.dto.OrderInfoDto;
import com.nbrt.kybao.mall.dto.common.ApiResult;
import com.nbrt.kybao.mall.model.entity.Orders;
import com.nbrt.kybao.mall.enums.CodeEnum;
import com.nbrt.kybao.mall.model.vo.OrderDetailVo;
import com.nbrt.kybao.mall.model.vo.OrderListVo;
import com.nbrt.kybao.mall.service.api.OrdersService;
import com.nbrt.kybao.mall.model.vo.OrderPageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@CrossOrigin
@RestController
@RequestMapping("/orders")
@Api(tags = "订单控制")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @ApiOperation("添加订单")
    @PostMapping("/one")
    public ApiResult<OrderInfoDto> getOne(@RequestBody OrderInfoDto orderInfoDto) {
        // 判断此订单编号是否存在
        if (orderIsExist(orderInfoDto.getOrderNo())) {
            return ApiResult.failedWithMessage(CodeEnum.REPEAT_ADD.getCode(), CodeEnum.REPEAT_ADD.getMessage());
        }
        // 拷贝信息
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderInfoDto, orders);
        boolean save = ordersService.save(orders);
        if (save) {
            return ApiResult.failedWithMessage(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage());
        }
        return ApiResult.failedWithMessage(CodeEnum.UNKNOWN.getCode(), CodeEnum.UNKNOWN.getMessage());
    }

    @ApiOperation("获取订单详情")
    @GetMapping("/{orderNo}")
    public ApiResult<OrderDetailVo> getOne(@PathVariable String orderNo) {
        // 判断订单编号是否为空
        if (StringUtils.isBlank(orderNo)) {
            return ApiResult.failedWithMessage(CodeEnum.NO_PARAM.getCode(), CodeEnum.NO_PARAM.getMessage());
        }
        // 判断订单是否存在
        if (orderIsExist(orderNo)) {
            OrderDetailVo orderDetailVo = ordersService.getOneByOrderNo(orderNo);
            return ApiResult.successWithMessageAndData("获取成功", orderDetailVo);
        }
        return ApiResult.failedWithMessage(CodeEnum.UN_EXIST.getCode(), CodeEnum.UN_EXIST.getMessage());
    }

    @ApiOperation("删除订单")
    @DeleteMapping("/{orderNo}")
    public ApiResult<String> deleteOne(@PathVariable String orderNo) {
        // 判断订单编号是否为空
        if (StringUtils.isBlank(orderNo)) {
            return ApiResult.failedWithMessage(CodeEnum.NO_PARAM.getCode(), CodeEnum.NO_PARAM.getMessage());
        }
        // 判断此订单是否存在
        if (orderIsExist(orderNo)) {
            // 执行删除操作
            QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Orders::getOrderNo, orderNo);
            boolean remove = ordersService.remove(queryWrapper);
            if (remove) {
                return ApiResult.failedWithMessage(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage());
            }
            return ApiResult.failedWithMessage(CodeEnum.UNKNOWN.getCode(), CodeEnum.UNKNOWN.getMessage());
        }
        return ApiResult.failedWithMessage(CodeEnum.UN_EXIST.getCode(), CodeEnum.UN_EXIST.getMessage());
    }

    @ApiOperation("获取订单列表")
    @PostMapping(value = "/getListInfo")
    public ApiResult<IPage<OrderListVo>> getAssessment(
            @RequestBody OrderPageInfoVo orderPageInfoVo
    ) {
        IPage<OrderListVo> list=ordersService.getListInfo(orderPageInfoVo);
        return ApiResult.successWithMessageAndData(CodeEnum.SUCCESS.getMessage(),list);
    }

    /**
     * 判断订单是否存在
     *
     * @param orderNo 订单编号
     * @return 存在与否
     */
    public Boolean orderIsExist(String orderNo) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Orders::getOrderNo, orderNo);
        Orders one = ordersService.getOne(queryWrapper);
        return one != null;
    }

}

