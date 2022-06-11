package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author hjh
 * @date 2022/6/2 21:42
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * (移动端)生成订单
     */

    int getOrder(Order order);
    //订单更新
    boolean updateOrder(Order order);

    /**
     * (移动端)预定成功页面-根据订单号
     * @param orderNo
     * @return
     */
    Order getOrder2(String orderNo);
    //修改订单状态:支付中→支付完成-根据订单号
    boolean updateOrderByOrderNo(String orderNo);

    /**
     * (移动端)取消订单-根据订单号
     * @param orderNo
     * @return
     */
    boolean cancelOrder(String orderNo);

    //(web端)录入住客信息-根据订单号
    boolean addResidentInformation(@Param("orderNo") String orderNo, @Param("fourlong") String fourlong);
    //(web端)查询住客信息-根据订单号
    Order findOrderResidentInformation(String orderNo);
    //(web端)完成订单-2.支付完成→3.已完成-根据订单号
    boolean finishOrder(String orderNo);

    //(web端)查询订单并分页-(酒店名)&(姓名)&(订单号)
    Page<Order> findOrderBy4(
            @Param("page") Page<Order> page,
            @Param("hotelName") String hotelName,
            @Param("residentName") String residentName,
            @Param("orderNo") String orderNo,
            @Param("state") int state
    );
    //(web端)订单详情-订单编号
    Order OrderDetailByOrderNo(String orderNo);
}
