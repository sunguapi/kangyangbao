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
     * 订单列表带分页
     * @param params 键值对参数
     * @return 订单分页列表信息
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

        //1.先查询出符合模糊条件的订单信息列表
        List<OrderEntity> orderEntities = page.getRecords();

        List<OrderListVo> orderListVos = orderEntities.stream().map(orderEntity -> {
            OrderListVo orderListVo = new OrderListVo();
            //订单基本信息
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
     * 订单确认页数据
     * @param token token令牌
     * @param serverItemId 服务项id
     * @return 订单确认页数据vo
     */
    @Override
    public OrderConfirmVo confirmOrder(String token, Long serverItemId) {

        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        String username = JwtHelper.getUserName(token);
        //设置用户收货地址信息

        R r = userFeignService.getUserDefaultReceiveByUsername(username);

        log.info("==================r==================>" + r);

        UserReceiveVo userReceiveVo = r.getData(new TypeReference<UserReceiveVo>(){});
        orderConfirmVo.setUserReceiveVo(userReceiveVo);
        ServerItemEntity serverItem = serverItemService.getById(serverItemId);
        R userInfo = userFeignService.getUserInfo(username);
        UserInfoVo userInfoVo = userInfo.getData(new TypeReference<UserInfoVo>(){});
        orderConfirmVo.setGender(userInfoVo.getUserGender());

        //设置服务项信息
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
        wrapper.eq("consumer_site","居家康养");
        ConsumptionEntity consumptionEntity = consumptionService.getOne(wrapper);

        log.info("==============consumptionEntity======================>" + consumptionEntity);

        //会员优惠价格
        BigDecimal price = serverItem.getPrice().multiply(consumptionEntity.getConsumerDiscount());
        orderConfirmVo.setMemberPrice(serverItem.getPrice().subtract(price));

        //积分优惠价格
        BigDecimal count = new BigDecimal(userInfoVo.getUserIntegral()).compareTo(new BigDecimal(consumptionEntity.getIntegralRate())) > 0 ? new BigDecimal(consumptionEntity.getIntegralRate()) : new BigDecimal(userInfoVo.getUserIntegral());
        BigDecimal multiply = count.multiply(consumptionEntity.getConsumerBili());
        orderConfirmVo.setConsumerDiscount(multiply);
        orderConfirmVo.setIntegralRate(consumptionEntity.getIntegralRate());

        //真实价格
        BigDecimal realPrice = serverItem.getPrice().subtract(orderConfirmVo.getMemberPrice()).subtract(multiply);
        orderConfirmVo.setRealPrice(realPrice);

        log.info("==============orderConfirmVo======================>" + orderConfirmVo);

        return orderConfirmVo;
    }

    /**
     * 订单提交
     * @param orderSubmitVo 前端传来的参数
     */
    @Override
    public void submitOrder(OrderSubmitVo orderSubmitVo) {
        OrderEntity orderEntity = new OrderEntity();

        //生成订单号
        String orderId = IdWorker.getIdStr();
        orderEntity.setOrderNo(orderId);

        //订单状态
        orderEntity.setOrderStatus(OrderStatusEnum.CREATE_NEW.getCode());

        //收货编号
        orderEntity.setReceiveNo(orderSubmitVo.getReceiveNo());

        //服务时间
        orderEntity.setServerTime(orderSubmitVo.getServerTime());

        //用户编号
        String token = orderSubmitVo.getToken();
        String userName = JwtHelper.getUserName(token);
        orderEntity.setUserNo(userName);

        //用户名字
        R r = userFeignService.getUserInfo(userName);
        if(r.getCode() == 200) {
            UserInfoVo userInfoVo = r.getData(new TypeReference<UserInfoVo>() {});
            orderEntity.setUserName(userInfoVo.getNikeName());
        }

        //服务项编号
        orderEntity.setPropertyNo(orderSubmitVo.getPropertyNo());

        //总价
        orderEntity.setTotalPrice(orderSubmitVo.getTotalPrice());

        //优惠
        orderEntity.setIntegral(orderSubmitVo.getIntegral());
        orderEntity.setIntegralDiscount(orderSubmitVo.getIntegralDiscount());
        orderEntity.setMemberPrice(orderSubmitVo.getMemberPrice());
        //优惠后价格
        orderEntity.setRealPrice(orderSubmitVo.getRealPrice());

        //订单时间
        orderEntity.setCreateTime(new Date());
        orderEntity.setUpdateTime(new Date());

        //保存订单
        this.save(orderEntity);


    }

    /**
     * 订单详情移动端
     * @param token token令牌
     * @return 订单详情页数据
     */
    @Override
    public OrderItemDetail getOrderDetail(String token) {

        OrderItemDetail orderItemDetail = new OrderItemDetail();
        String userName = JwtHelper.getUserName(token);

        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",userName);
        OrderEntity orderEntity = this.getOne(wrapper);

        //订单编号
        orderItemDetail.setOrderNo(orderEntity.getOrderNo());

        //服务时间
        orderItemDetail.setServerTime(orderEntity.getServerTime());

        //地址信息
        R r = userFeignService.getReceiveById(orderEntity.getReceiveNo());
        if(r.getCode() == 200) {
            UserReceiveVo userReceiveVo = r.getData(new TypeReference<UserReceiveVo>() {});
            BeanUtils.copyProperties(userReceiveVo,userReceiveVo);
        }

        //服务项基本信息
        ServerItemEntity serverItemEntity = serverItemService.getById(orderEntity.getPropertyNo());
        OrderServerItemVo orderServerItemVo = new OrderServerItemVo();
        orderServerItemVo.setServerItemId(serverItemEntity.getServerItemId());
        orderServerItemVo.setServerItemName(serverItemEntity.getServerItemName());
        orderServerItemVo.setDescript(serverItemEntity.getDescript());

        //服务项图片集
        QueryWrapper<ServerItemImagesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("server_item_id",serverItemEntity.getServerItemId());
        List<ServerItemImagesEntity> imagesEntities = serverItemImagesService.list(queryWrapper);
        for (ServerItemImagesEntity imagesEntity : imagesEntities) {
            if(imagesEntity.getDefaultImg() == 1) {
                orderServerItemVo.setImage(imagesEntity.getImgUrl());
            }
        }
        orderItemDetail.setOrderServerItemVo(orderServerItemVo);

        //实付款
        PayinfoEntity payinfoEntity = payinfoService.getById(orderEntity.getPayNo());
        orderItemDetail.setRealPrice(payinfoEntity.getAmount());

        //总价
        orderItemDetail.setTotalPrice(orderEntity.getTotalPrice());

        return orderItemDetail;
    }

    /**
     * 取消订单
     * @param orderNo 订单编号
     */
    @Override
    public void cancelOrder(String orderNo) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        OrderEntity orderEntity = this.getOne(queryWrapper);
        orderEntity.setOrderStatus(OrderStatusEnum.CANCLED.getCode());
        //TODO 退款相关
    }

    /**
     * 管理端订单详情
     * @param orderNo 订单编号
     * @return 返回订单详情页Vo数据
     */
    @Override
    public OrderAdminDetailVo getAdminOrderDetail(String orderNo) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        OrderEntity orderEntity = this.getOne(queryWrapper);
        OrderAdminDetailVo orderAdminDetailVo = new OrderAdminDetailVo();
        //订单相关信息
        orderAdminDetailVo.setOrderNo(orderEntity.getOrderNo());
        orderAdminDetailVo.setPayNo(orderEntity.getPayNo());
        orderAdminDetailVo.setDisCountType("无");
        QueryWrapper<PayinfoEntity> payinfoEntityQueryWrapper = new QueryWrapper<>();
        payinfoEntityQueryWrapper.eq("pay_no",orderEntity.getPayNo());
        PayinfoEntity payinfoEntity = payinfoService.getOne(payinfoEntityQueryWrapper);
        orderAdminDetailVo.setDisCount(orderEntity.getTotalPrice().subtract(payinfoEntity.getAmount()));
        orderAdminDetailVo.setRealPrice(payinfoEntity.getAmount());
        orderAdminDetailVo.setPayType(payinfoEntity.getPaymentType());
        orderAdminDetailVo.setCreateTime(orderEntity.getCreateTime().toString());
        //收货相关信息
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
            log.error("收货调用失败");
        }
        //服务项相关信息
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
        //备注信息
        orderAdminDetailVo.setRemark(orderEntity.getRemark());
        return orderAdminDetailVo;
    }

    /**
     * 订单管理端退款详情
     * @param orderNo 订单编号
     * @return 返回订单退款页面数据
     */
    @Override
    public OrderAdminRefoundVo getRefoundDetail(String orderNo) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        OrderEntity orderEntity = this.getOne(queryWrapper);
        OrderAdminRefoundVo orderAdminRefoundDetailVo = new OrderAdminRefoundVo();
        //订单相关信息
        orderAdminRefoundDetailVo.setOrderNo(orderEntity.getOrderNo());
        orderAdminRefoundDetailVo.setPayNo(orderEntity.getPayNo());
        orderAdminRefoundDetailVo.setDisCountType("无");
        QueryWrapper<PayinfoEntity> payinfoEntityQueryWrapper = new QueryWrapper<>();
        payinfoEntityQueryWrapper.eq("pay_no",orderEntity.getPayNo());
        PayinfoEntity payinfoEntity = payinfoService.getOne(payinfoEntityQueryWrapper);
        orderAdminRefoundDetailVo.setDisCount(orderEntity.getTotalPrice().subtract(payinfoEntity.getAmount()));
        orderAdminRefoundDetailVo.setRealPrice(payinfoEntity.getAmount());
        orderAdminRefoundDetailVo.setPayType(payinfoEntity.getPaymentType());
        orderAdminRefoundDetailVo.setCreateTime(orderEntity.getCreateTime().toString());
        //收货相关信息
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
            log.error("收货调用失败");
        }
        //退款相关信息
        QueryWrapper<RefundEntity> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("refund_no",orderEntity.getRefundNo());
        RefundEntity refundEntity = refundService.getOne(objectQueryWrapper);
        orderAdminRefoundDetailVo.setRefoundType(refundEntity.getPaymentType());
        orderAdminRefoundDetailVo.setRefoundNo(refundEntity.getRefundNo());
        orderAdminRefoundDetailVo.setRefoundStatus(refundEntity.getRefundStatus());
        orderAdminRefoundDetailVo.setRealPrice(refundEntity.getAmount());
        orderAdminRefoundDetailVo.setTotalPrice(refundEntity.getAmount());
        //备注信息
        orderAdminRefoundDetailVo.setRemark(orderEntity.getRemark());
        return orderAdminRefoundDetailVo;
    }
}