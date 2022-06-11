package com.nbrt.kybao.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.mall.dto.OrderInfoDto;
import com.nbrt.kybao.mall.model.entity.Orders;
import com.nbrt.kybao.mall.mapper.OrdersMapper;
import com.nbrt.kybao.mall.model.vo.CommodityInfoVo;
import com.nbrt.kybao.mall.model.vo.OrderDetailVo;
import com.nbrt.kybao.mall.model.vo.OrderListVo;
import com.nbrt.kybao.mall.service.api.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.mall.model.vo.OrderPageInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022 -05-05
 */
@Slf4j
@Service
@Transactional
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public OrderDetailVo getOneByOrderNo(String orderNo) {
        // 首先进行订单详情部分查询
        OrderDetailVo orderDetailVo = ordersMapper.selectByNo(orderNo);
        // 给详情信息填补商品的信息
        List<CommodityInfoVo> list = ordersMapper.selectCommodityListByNo(orderNo);
        orderDetailVo.setCommodityInfoVoList(list);
        BigDecimal count= BigDecimal.valueOf(0);
        for (CommodityInfoVo infoVo : list) {
            // 将数量转换为BigDecimal
            BigDecimal value = BigDecimal.valueOf(infoVo.getProCount());
            // 乘法运算
            BigDecimal multiply = infoVo.getCommodityPrice().multiply(value);
            // 价格累加
            count = count.add(multiply);
        }
        orderDetailVo.setTotalPrice(count);
        // 减掉运费
        BigDecimal subtract = count.subtract(orderDetailVo.getPostage());
        // 应当支付
        orderDetailVo.setOrderAmount(subtract);
        return orderDetailVo;
    }

    @Override
    public IPage<OrderListVo> getListInfo(OrderPageInfoVo orderPageInfoVo) {
        // 新建分页
        Page<OrderListVo> page = new Page<>(
                Long.parseLong(orderPageInfoVo.getCurrentPage()),
                Long.parseLong(orderPageInfoVo.getPageSize()));
        System.out.println(page);
        IPage<OrderListVo> iPage = ordersMapper.queryPageInfo(
                page,
                orderPageInfoVo.getStartTime(),
                orderPageInfoVo.getEndTime(),
                orderPageInfoVo.getSearchString()
        );
        System.out.println(iPage.getRecords().size());
        return iPage;
    }
}
