package com.nbrt.kybao.homecare.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.nbrt.kybao.homecare.constant.OrderStatusEnum;
import com.nbrt.kybao.homecare.entity.*;
import com.nbrt.kybao.homecare.feign.UserFeignService;
import com.nbrt.kybao.homecare.service.*;
import com.nbrt.kybao.homecare.utils.*;
import com.nbrt.kybao.homecare.vo.order.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.homecare.dao.OrderDao;

@Slf4j
@Service("homecareOrderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    private final ServerItemService serverItemService;
    private final UserFeignService userFeignService;
    private final ServerItemImagesService serverItemImagesService;
    private final PayinfoService payinfoService;
    private final ServerServeritemRelationService serverServeritemRelationService;
    private final ServerService serverService;
    private final RefundService refundService;
    private final ConsumptionService consumptionService;

    public OrderServiceImpl(ServerItemService serverItemService, UserFeignService userFeignService, ServerItemImagesService serverItemImagesService, PayinfoService payinfoService, ServerServeritemRelationService serverServeritemRelationService, ServerService serverService, RefundService refundService, ConsumptionService consumptionService) {
        this.serverItemService = serverItemService;
        this.userFeignService = userFeignService;
        this.serverItemImagesService = serverItemImagesService;
        this.payinfoService = payinfoService;
        this.serverServeritemRelationService = serverServeritemRelationService;
        this.serverService = serverService;
        this.refundService = refundService;
        this.consumptionService = consumptionService;
    }

    /**
     * ?????????????????????
     * @param params ???????????????
     * @return ????????????????????????
     */
    @Override
    public MyPageUtils<OrderListVo> queryPage(Map<String, Object> params) {


        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(StringUtils.isNotEmpty(key) && !key.equals("\"\"")){
            queryWrapper.like("user_no",key);

        }

        queryWrapper.orderByDesc("create_time");

        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params,null,true),
                queryWrapper
        );

        //1.???????????????????????????????????????????????????
        List<OrderEntity> orderEntities = page.getRecords();

        List<OrderListVo> orderListVos = orderEntities.stream().map(orderEntity -> {
            OrderListVo orderListVo = new OrderListVo();
            //??????????????????
            orderListVo.setOrderNo(orderEntity.getOrderNo());
            orderListVo.setIntegralDiscount(orderEntity.getIntegralDiscount());
            orderListVo.setMemberPrice(orderEntity.getMemberPrice());
            orderListVo.setRealPrice(orderEntity.getRealPrice());
            orderListVo.setOrderStatus(orderEntity.getOrderStatus());
            orderListVo.setUserNo(orderEntity.getUserNo());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(orderEntity.getCreateTime());
            orderListVo.setCreateTime(format);
            return orderListVo;
        }).collect(Collectors.toList());


//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Collections.sort(orderListVos, new Comparator<OrderListVo>() {
//            @SneakyThrows
//            @Override
//            public int compare(OrderListVo o1, OrderListVo o2) {
//                Date dt1 = format.parse(o1.getCreateTime());
//                Date dt2 = format.parse(o2.getCreateTime());
//                if (dt1.getTime() > dt2.getTime()) {
//                return 1;
//                } else if (dt1.getTime() < dt2.getTime()) {
//                return -1;
//                } else {
//                return 0;
//                }
//            }
//        });


        MyPageUtils<OrderListVo> myPageUtils = new MyPageUtils<>();
        myPageUtils.setList(orderListVos);
        myPageUtils.setTotalCount(page.getTotal());
        Object pageSize = params.get("pageSize");
        double totalPage = Math.ceil(Double.valueOf(String.valueOf(orderEntities.size())) / Double.valueOf(pageSize.toString()));
        myPageUtils.setTotalPage(new Double(totalPage).longValue());

        myPageUtils.setCurrPage(page.getCurrent());
        myPageUtils.setPageSize(Integer.parseInt(pageSize.toString()));
        return myPageUtils;
    }

    /**
     * ?????????????????????
     * @param token token??????
     * @param serverItemId ?????????id
     * @return ?????????????????????vo
     */
    @Override
    public OrderConfirmVo confirmOrder(String token, Long serverItemId) {

        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        String username = JwtHelper.getUserName(token);
        //??????????????????????????????

        R r = userFeignService.getUserDefaultReceiveByUsername(username);

        log.info("==================r==================>" + r);

        UserReceiveVo userReceiveVo = r.getData(new TypeReference<UserReceiveVo>(){});
        orderConfirmVo.setUserReceiveVo(userReceiveVo);
        ServerItemEntity serverItem = serverItemService.getById(serverItemId);
        R userInfo = userFeignService.getUserInfo(username);
        UserInfoVo userInfoVo = userInfo.getData(new TypeReference<UserInfoVo>(){});
        orderConfirmVo.setGender(userInfoVo.getUserGender());

        //?????????????????????
        OrderServerItemVo orderServerItemVo = new OrderServerItemVo();

        log.info("==============orderServerItemVo======================>" + orderServerItemVo);

        orderServerItemVo.setServerItemId(serverItemId);
        orderServerItemVo.setServerItemName(serverItem.getServerItemName());
        orderServerItemVo.setServerItemCount(1);
        orderServerItemVo.setServerItemPrice(serverItem.getPrice());
        orderServerItemVo.setDescript(serverItem.getDescript());
        QueryWrapper<ServerItemImagesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_item_id",serverItemId);
        List<ServerItemImagesEntity> serverItemImagesEntities = serverItemImagesService.list(queryWrapper);
        for (ServerItemImagesEntity serverItemImagesEntity : serverItemImagesEntities) {
            if(serverItemImagesEntity.getDefaultImg() == 1) {
                orderServerItemVo.setImage(serverItemImagesEntity.getImgUrl());
            }
        }
        orderConfirmVo.setOrderServerItemVo(orderServerItemVo);

        QueryWrapper<ConsumptionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_grade",userInfoVo.getUserGrade());
        wrapper.eq("consumer_site","????????????");
        ConsumptionEntity consumptionEntity = consumptionService.getOne(wrapper);

        log.info("==============consumptionEntity======================>" + consumptionEntity);

        //??????????????????
        BigDecimal price = serverItem.getPrice().multiply(consumptionEntity.getConsumerDiscount());
        orderConfirmVo.setMemberPrice(serverItem.getPrice().subtract(price));

        //??????????????????
        BigDecimal count = new BigDecimal(userInfoVo.getUserIntegral()).compareTo(new BigDecimal(consumptionEntity.getIntegralRate())) > 0 ? new BigDecimal(consumptionEntity.getIntegralRate()) : new BigDecimal(userInfoVo.getUserIntegral());
        BigDecimal multiply = count.multiply(consumptionEntity.getConsumerBili());
        orderConfirmVo.setConsumerDiscount(multiply);
        orderConfirmVo.setIntegralRate(consumptionEntity.getIntegralRate());

        //????????????
        BigDecimal realPrice = serverItem.getPrice().subtract(orderConfirmVo.getMemberPrice()).subtract(multiply);
        orderConfirmVo.setRealPrice(realPrice);

        log.info("==============orderConfirmVo======================>" + orderConfirmVo);

        return orderConfirmVo;
    }

    /**
     * ????????????
     * @param orderSubmitVo ?????????????????????
     */
    @Override
    public void submitOrder(OrderSubmitVo orderSubmitVo) {
        OrderEntity orderEntity = new OrderEntity();

        //???????????????
        String orderId = IdWorker.getIdStr();
        orderEntity.setOrderNo(orderId);

        //????????????
        orderEntity.setOrderStatus(OrderStatusEnum.CREATE_NEW.getCode());

        //????????????
        orderEntity.setReceiveNo(orderSubmitVo.getReceiveNo());

        //????????????
        orderEntity.setServerTime(orderSubmitVo.getServerTime());

        //????????????
        String token = orderSubmitVo.getToken();
        String userName = JwtHelper.getUserName(token);
        orderEntity.setUserNo(userName);

        //????????????
        R r = userFeignService.getUserInfo(userName);
        if(r.getCode() == 200) {
            UserInfoVo userInfoVo = r.getData(new TypeReference<UserInfoVo>() {});
            orderEntity.setUserName(userInfoVo.getNikeName());
        }

        //???????????????
        orderEntity.setPropertyNo(orderSubmitVo.getPropertyNo());

        //??????
        orderEntity.setTotalPrice(orderSubmitVo.getTotalPrice());

        //??????
        orderEntity.setIntegral(orderSubmitVo.getIntegral());
        orderEntity.setIntegralDiscount(orderSubmitVo.getIntegralDiscount());
        orderEntity.setMemberPrice(orderSubmitVo.getMemberPrice());
        //???????????????
        orderEntity.setRealPrice(orderSubmitVo.getRealPrice());

        //????????????
        orderEntity.setCreateTime(new Date());
        orderEntity.setUpdateTime(new Date());

        //????????????
        this.save(orderEntity);


    }

    /**
     * ?????????????????????
     * @param token token??????
     * @return ?????????????????????
     */
    @Override
    public OrderItemDetail getOrderDetail(String token) {

        OrderItemDetail orderItemDetail = new OrderItemDetail();
        String userName = JwtHelper.getUserName(token);

        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",userName);
        OrderEntity orderEntity = this.getOne(wrapper);

        //????????????
        orderItemDetail.setOrderNo(orderEntity.getOrderNo());

        //????????????
        orderItemDetail.setServerTime(orderEntity.getServerTime());

        //????????????
        R r = userFeignService.getReceiveById(orderEntity.getReceiveNo());
        if(r.getCode() == 200) {
            UserReceiveVo userReceiveVo = r.getData(new TypeReference<UserReceiveVo>() {});
            BeanUtils.copyProperties(userReceiveVo,userReceiveVo);
        }

        //?????????????????????
        ServerItemEntity serverItemEntity = serverItemService.getById(orderEntity.getPropertyNo());
        OrderServerItemVo orderServerItemVo = new OrderServerItemVo();
        orderServerItemVo.setServerItemId(serverItemEntity.getServerItemId());
        orderServerItemVo.setServerItemName(serverItemEntity.getServerItemName());
        orderServerItemVo.setDescript(serverItemEntity.getDescript());

        //??????????????????
        QueryWrapper<ServerItemImagesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_item_id",serverItemEntity.getServerItemId());
        List<ServerItemImagesEntity> imagesEntities = serverItemImagesService.list(queryWrapper);
        for (ServerItemImagesEntity imagesEntity : imagesEntities) {
            if(imagesEntity.getDefaultImg() == 1) {
                orderServerItemVo.setImage(imagesEntity.getImgUrl());
            }
        }
        orderItemDetail.setOrderServerItemVo(orderServerItemVo);

        //?????????
        PayinfoEntity payinfoEntity = payinfoService.getById(orderEntity.getPayNo());
        orderItemDetail.setRealPrice(payinfoEntity.getAmount());

        //??????
        orderItemDetail.setTotalPrice(orderEntity.getTotalPrice());

        return orderItemDetail;
    }

    /**
     * ????????????
     * @param orderNo ????????????
     */
    @Override
    public void cancelOrder(String orderNo) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        OrderEntity orderEntity = this.getOne(queryWrapper);
        orderEntity.setOrderStatus(OrderStatusEnum.CANCLED.getCode());
        //TODO ????????????
    }

    /**
     * ?????????????????????
     * @param orderNo ????????????
     * @return ?????????????????????Vo??????
     */
    @Override
    public OrderAdminDetailVo getAdminOrderDetail(String orderNo) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        OrderEntity orderEntity = this.getOne(queryWrapper);
        OrderAdminDetailVo orderAdminDetailVo = new OrderAdminDetailVo();
        //??????????????????
        orderAdminDetailVo.setOrderNo(orderEntity.getOrderNo());
        orderAdminDetailVo.setPayNo(orderEntity.getPayNo());
        orderAdminDetailVo.setDisCountType("???");
        QueryWrapper<PayinfoEntity> payinfoEntityQueryWrapper = new QueryWrapper<>();
        payinfoEntityQueryWrapper.eq("pay_no",orderEntity.getPayNo());
        PayinfoEntity payinfoEntity = payinfoService.getOne(payinfoEntityQueryWrapper);
        orderAdminDetailVo.setDisCount(orderEntity.getTotalPrice().subtract(payinfoEntity.getAmount()));
        orderAdminDetailVo.setRealPrice(payinfoEntity.getAmount());
        orderAdminDetailVo.setPayType(payinfoEntity.getPaymentType());
        orderAdminDetailVo.setCreateTime(orderEntity.getCreateTime().toString());
        //??????????????????
        R r = userFeignService.getReceiveById(orderEntity.getReceiveNo());
        if(r.getCode() == 200) {
            UserReceiveVo userReceiveVo = r.getData(new TypeReference<UserReceiveVo>() {});
            orderAdminDetailVo.setReceiveName(userReceiveVo.getReceiveName());
            orderAdminDetailVo.setReceivePhone(userReceiveVo.getReceivePhone());
            orderAdminDetailVo.setReceiveProvince(userReceiveVo.getReceiveProvince());
            orderAdminDetailVo.setReceiveCity(userReceiveVo.getReceiveCity());
            orderAdminDetailVo.setReceiveDistrict(userReceiveVo.getReceiveDistrict());
            orderAdminDetailVo.setReceiveAddress(userReceiveVo.getReceiveAddress());
        } else {
            log.error("??????????????????");
        }
        //?????????????????????
        QueryWrapper<ServerItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("server_item_id",orderEntity.getPropertyNo());
        ServerItemEntity serverItemEntity = serverItemService.getOne(wrapper);
        orderAdminDetailVo.setServerItemName(serverItemEntity.getServerItemName());
        orderAdminDetailVo.setServerItemPrice(serverItemEntity.getPrice());
        orderAdminDetailVo.setServerItemTime(serverItemEntity.getServerTime().toString());
        QueryWrapper<ServerServeritemRelationEntity> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("server_item_id",serverItemEntity.getServerItemId());
        ServerServeritemRelationEntity relationServiceOne = serverServeritemRelationService.getOne(objectQueryWrapper);
        QueryWrapper<ServerEntity> w = new QueryWrapper<>();
        w.eq("server_id",relationServiceOne.getServerId());
        ServerEntity serverEntity = serverService.getOne(w);
        orderAdminDetailVo.setServerType(serverEntity.getServerName());
        //????????????
        orderAdminDetailVo.setRemark(orderEntity.getRemark());
        return orderAdminDetailVo;
    }

    /**
     * ???????????????????????????
     * @param orderNo ????????????
     * @return ??????????????????????????????
     */
    @Override
    public OrderAdminRefoundVo getRefoundDetail(String orderNo) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        OrderEntity orderEntity = this.getOne(queryWrapper);
        OrderAdminRefoundVo orderAdminRefoundDetailVo = new OrderAdminRefoundVo();
        //??????????????????
        orderAdminRefoundDetailVo.setOrderNo(orderEntity.getOrderNo());
        orderAdminRefoundDetailVo.setPayNo(orderEntity.getPayNo());
        orderAdminRefoundDetailVo.setDisCountType("???");
        QueryWrapper<PayinfoEntity> payinfoEntityQueryWrapper = new QueryWrapper<>();
        payinfoEntityQueryWrapper.eq("pay_no",orderEntity.getPayNo());
        PayinfoEntity payinfoEntity = payinfoService.getOne(payinfoEntityQueryWrapper);
        orderAdminRefoundDetailVo.setDisCount(orderEntity.getTotalPrice().subtract(payinfoEntity.getAmount()));
        orderAdminRefoundDetailVo.setRealPrice(payinfoEntity.getAmount());
        orderAdminRefoundDetailVo.setPayType(payinfoEntity.getPaymentType());
        orderAdminRefoundDetailVo.setCreateTime(orderEntity.getCreateTime().toString());
        //??????????????????
        R r = userFeignService.getReceiveById(orderEntity.getReceiveNo());
        if(r.getCode() == 200) {
            UserReceiveVo userReceiveVo = r.getData(new TypeReference<UserReceiveVo>() {});
            orderAdminRefoundDetailVo.setReceiveName(userReceiveVo.getReceiveName());
            orderAdminRefoundDetailVo.setReceivePhone(userReceiveVo.getReceivePhone());
            orderAdminRefoundDetailVo.setReceiveProvince(userReceiveVo.getReceiveProvince());
            orderAdminRefoundDetailVo.setReceiveCity(userReceiveVo.getReceiveCity());
            orderAdminRefoundDetailVo.setReceiveDistrict(userReceiveVo.getReceiveDistrict());
            orderAdminRefoundDetailVo.setReceiveAddress(userReceiveVo.getReceiveAddress());
        } else {
            log.error("??????????????????");
        }
        //??????????????????
        QueryWrapper<RefundEntity> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("refund_no",orderEntity.getRefundNo());
        RefundEntity refundEntity = refundService.getOne(objectQueryWrapper);
        orderAdminRefoundDetailVo.setRefoundType(refundEntity.getPaymentType());
        orderAdminRefoundDetailVo.setRefoundNo(refundEntity.getRefundNo());
        orderAdminRefoundDetailVo.setRefoundStatus(refundEntity.getRefundStatus());
        orderAdminRefoundDetailVo.setRealPrice(refundEntity.getAmount());
        orderAdminRefoundDetailVo.setTotalPrice(refundEntity.getAmount());
        //????????????
        orderAdminRefoundDetailVo.setRemark(orderEntity.getRemark());
        return orderAdminRefoundDetailVo;
    }
}