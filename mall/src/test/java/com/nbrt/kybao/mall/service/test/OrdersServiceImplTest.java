package com.nbrt.kybao.mall.service.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nbrt.kybao.mall.dto.OrderInfoDto;
import com.nbrt.kybao.mall.model.vo.OrderDetailVo;
import com.nbrt.kybao.mall.model.vo.OrderListVo;
import com.nbrt.kybao.mall.service.api.OrdersService;
import com.nbrt.kybao.mall.model.vo.OrderPageInfoVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author hjh
 * @date 2022/5/9 10:51
 */
@SpringBootTest
public class OrdersServiceImplTest {

    @Autowired
    private OrdersService ordersService;

    @Test
    void getOneByOrderNo() {
        OrderDetailVo oneByOrderNo = ordersService.getOneByOrderNo("123459678645451");
        System.out.println(oneByOrderNo);
    }

    @Test
    void getList() {
        OrderPageInfoVo orderPageInfoVo=new OrderPageInfoVo();
        orderPageInfoVo.setCurrentPage("1");
        orderPageInfoVo.setPageSize("3");
        IPage<OrderListVo> listInfo = ordersService.getListInfo(orderPageInfoVo);
        listInfo.getRecords().forEach(System.out::println);
    }
}