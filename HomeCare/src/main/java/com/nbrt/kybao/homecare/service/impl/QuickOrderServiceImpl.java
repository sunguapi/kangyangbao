package com.nbrt.kybao.homecare.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.nbrt.kybao.homecare.constant.OrderStatusEnum;
import com.nbrt.kybao.homecare.dao.QuickOrderDao;
import com.nbrt.kybao.homecare.entity.ConsumptionEntity;
import com.nbrt.kybao.homecare.entity.QuickOrderEntity;
import com.nbrt.kybao.homecare.entity.ServerItemEntity;
import com.nbrt.kybao.homecare.entity.ServerItemImagesEntity;
import com.nbrt.kybao.homecare.feign.UserFeignService;
import com.nbrt.kybao.homecare.service.*;
import com.nbrt.kybao.homecare.utils.JwtHelper;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import com.nbrt.kybao.homecare.utils.R;
import com.nbrt.kybao.homecare.vo.order.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;




@Service("homecareQuickOrderService")
public class QuickOrderServiceImpl extends ServiceImpl<QuickOrderDao, QuickOrderEntity> implements QuickOrderService {

    private final OrderService orderService;
    private final UserFeignService userFeignService;
    private final ServerItemService serverItemService;
    private final ConsumptionService consumptionService;
    private final ServerItemImagesService serverItemImagesService;

    public QuickOrderServiceImpl(OrderService orderService, UserFeignService userFeignService, ServerItemService serverItemService, ConsumptionService consumptionService, ServerItemImagesService serverItemImagesService) {
        this.orderService = orderService;
        this.userFeignService = userFeignService;
        this.serverItemService = serverItemService;
        this.consumptionService = consumptionService;
        this.serverItemImagesService = serverItemImagesService;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QuickOrderEntity> page = this.page(
                new Query<QuickOrderEntity>().getPage(params),
                new QueryWrapper<QuickOrderEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * ??????????????????
     * @param orderSubmitQuickVo ?????????????????????
     */
    @Override
    public void submitQuickOrder(OrderSubmitQuickVo orderSubmitQuickVo) {
        QuickOrderEntity homecareQuickOrderEntity = new QuickOrderEntity();

        //???????????????
        String orderId = IdWorker.getIdStr();
        homecareQuickOrderEntity.setOrderNo(orderId);

        //????????????
        homecareQuickOrderEntity.setOrderStatus(OrderStatusEnum.CREATE_NEW.getCode());

        //????????????
        homecareQuickOrderEntity.setReceiveNo(orderSubmitQuickVo.getReceiveNo());

        //????????????
        homecareQuickOrderEntity.setServerTime(orderSubmitQuickVo.getServerTime());

        //????????????
        String token = orderSubmitQuickVo.getToken();
        String userName = JwtHelper.getUserName(token);
        homecareQuickOrderEntity.setUserNo(userName);

        //????????????
        R r = userFeignService.getUserInfo(userName);
        if(r.getCode() == 200) {
            UserInfoVo userInfoVo = r.getData(new TypeReference<UserInfoVo>() {});
            homecareQuickOrderEntity.setUserName(userInfoVo.getNikeName());
        }

        //???????????????
        homecareQuickOrderEntity.setPropertyNo(orderSubmitQuickVo.getPropertyNo());

        //??????
        homecareQuickOrderEntity.setTotalPrice(orderSubmitQuickVo.getTotalPrice());

        //??????
        homecareQuickOrderEntity.setIntegral(orderSubmitQuickVo.getIntegral());
        homecareQuickOrderEntity.setIntegralDiscount(orderSubmitQuickVo.getIntegralDiscount());
        homecareQuickOrderEntity.setMemberPrice(orderSubmitQuickVo.getMemberPrice());
        //???????????????
        homecareQuickOrderEntity.setRealPrice(orderSubmitQuickVo.getRealPrice());

        //??????????????????
        homecareQuickOrderEntity.setQuickName(orderSubmitQuickVo.getQuickName());

        //????????????
        homecareQuickOrderEntity.setCreateTime(new Date());
        homecareQuickOrderEntity.setUpdateTime(new Date());

        //????????????
        this.save(homecareQuickOrderEntity);
    }


    /**
     * ?????????????????????
     * @return ?????????????????????vo
     */
    @Override
    public OrderConfirmVo confirmOrder(String id) {
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        QueryWrapper<QuickOrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",id);
        QuickOrderEntity orderEntity = this.getOne(wrapper);

        orderConfirmVo.setServerTime(orderEntity.getServerTime());
        //??????????????????????????????
        R r = userFeignService.getUserDefaultReceiveByUsername(orderEntity.getUserNo());
        UserReceiveVo userReceiveVo = r.getData(new TypeReference<UserReceiveVo>(){});
        orderConfirmVo.setUserReceiveVo(userReceiveVo);
        ServerItemEntity serverItem = serverItemService.getById(orderEntity.getPropertyNo());
        R userInfo = userFeignService.getUserInfo(orderEntity.getUserNo());
        UserInfoVo userInfoVo = userInfo.getData(new TypeReference<UserInfoVo>(){});
        orderConfirmVo.setGender(userInfoVo.getUserGender());

        //?????????????????????
        OrderServerItemVo orderServerItemVo = new OrderServerItemVo();
        orderServerItemVo.setServerItemId(Long.parseLong(orderEntity.getPropertyNo()));
        orderServerItemVo.setServerItemName(serverItem.getServerItemName());
        orderServerItemVo.setServerItemCount(1);
        orderServerItemVo.setServerItemPrice(serverItem.getPrice());
        orderServerItemVo.setDescript(serverItem.getDescript());
        QueryWrapper<ServerItemImagesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_item_id",Long.parseLong(orderEntity.getPropertyNo()));
        List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(queryWrapper);
        for (ServerItemImagesEntity serverItemImagesEntity : serverItemImagesEntities) {
            if(serverItemImagesEntity.getDefaultImg() == 1) {
                orderServerItemVo.setImage(serverItemImagesEntity.getImgUrl());
            }
        }
        orderConfirmVo.setOrderServerItemVo(orderServerItemVo);

        QueryWrapper<ConsumptionEntity> consumptionEntityQueryWrapper = new QueryWrapper<>();
        consumptionEntityQueryWrapper.eq("user_grade",userInfoVo.getUserGrade());
        consumptionEntityQueryWrapper.eq("consumer_site","????????????");
        ConsumptionEntity consumptionEntity = consumptionService.getOne(consumptionEntityQueryWrapper);


        //??????????????????
        BigDecimal price = serverItem.getPrice().multiply(consumptionEntity.getConsumerDiscount());
        orderConfirmVo.setMemberPrice(serverItem.getPrice().subtract(price));

        //??????????????????
        BigDecimal multiply = new BigDecimal(consumptionEntity.getIntegralRate()).multiply(consumptionEntity.getConsumerBili());
        orderConfirmVo.setConsumerDiscount(multiply);
        orderConfirmVo.setIntegralRate(consumptionEntity.getIntegralRate());

        //????????????
        BigDecimal realPrice = serverItem.getPrice().subtract(orderConfirmVo.getMemberPrice()).subtract(multiply);
        orderConfirmVo.setRealPrice(realPrice);
        return orderConfirmVo;
    }

    /**
     * ????????????????????????
     * @param token token??????
     * @return ????????????????????????VO??????
     */
    @Override
    public List<OrderQuickDataVo> getQuickOrderList(String token) {
        String userName = JwtHelper.getUserName(token);
        QueryWrapper<QuickOrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_no",userName);
        List<QuickOrderEntity> quickOrderEntities = this.list(wrapper);
        List<OrderQuickDataVo> orderQuickDataVos = quickOrderEntities.stream().map(item -> {
            OrderQuickDataVo quickDataVo = new OrderQuickDataVo();
            quickDataVo.setQuickName(item.getQuickName());
            quickDataVo.setIcon(item.getIcon());
            quickDataVo.setId(item.getOrderNo());
            return quickDataVo;
        }).collect(Collectors.toList());
        return orderQuickDataVos;
    }

//    /**
//     * ?????????????????????????????????
//     * @param id ??????????????????
//     * @return ????????????????????????
//     */
//    @Override
//    public OrderConfirmVo getQuickInfoData(String id) {
//        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
//        QueryWrapper<QuickOrderEntity> wrapper = new QueryWrapper<>();
//        wrapper.eq("order_no",id);
//        QuickOrderEntity orderEntity = this.getOne(wrapper);
//        BeanUtils.copyProperties(orderEntity,orderConfirmVo);
//        return orderConfirmVo;
//    }

}