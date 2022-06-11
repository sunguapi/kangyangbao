package com.nbrt.kybao.mall.service.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nbrt.kybao.mall.model.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.mall.model.vo.OrderDetailVo;
import com.nbrt.kybao.mall.model.vo.OrderListVo;
import com.nbrt.kybao.mall.model.vo.OrderPageInfoVo;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
public interface OrdersService extends IService<Orders> {

    OrderDetailVo getOneByOrderNo(String orderNo);

    IPage<OrderListVo> getListInfo(OrderPageInfoVo orderPageInfoVo);
}
