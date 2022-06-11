package com.nbrt.kybao.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.mall.dto.OrderInfoDto;
import com.nbrt.kybao.mall.model.entity.Orders;
import com.nbrt.kybao.mall.model.vo.CommodityInfoVo;
import com.nbrt.kybao.mall.model.vo.OrderDetailVo;
import com.nbrt.kybao.mall.model.vo.OrderListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author hjh
 * @since 2022 -05-05
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 根据订单编号获取订单信息
     *
     * @param orderNo the order no
     * @return the order detail info vo
     */
    OrderDetailVo selectByNo(String orderNo);

    /**
     * 获取订单列表（模糊查询分页）
     *
     * @param page         the page
     * @param startTime    the start time
     * @param endTime      the end time
     * @param searchString the search string
     * @return the page
     */
    Page<OrderListVo> queryPageInfo(
            @Param("page") Page<OrderListVo> page,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("like") String searchString
    );

    /**
     * Select commodity list by no list.
     *
     * @param orderNo the order no
     * @return the list
     */
    List<CommodityInfoVo> selectCommodityListByNo(String orderNo);
}
