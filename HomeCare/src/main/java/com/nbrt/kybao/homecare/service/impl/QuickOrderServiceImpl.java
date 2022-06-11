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
     * 快捷订单提交
     * @param orderSubmitQuickVo 前端传来的参数
     */
    @Override
    public void submitQuickOrder(OrderSubmitQuickVo orderSubmitQuickVo) {
        QuickOrderEntity homecareQuickOrderEntity = new QuickOrderEntity();

        //生成订单号
        String orderId = IdWorker.getIdStr();
        homecareQuickOrderEntity.setOrderNo(orderId);

        //订单状态
        homecareQuickOrderEntity.setOrderStatus(OrderStatusEnum.CREATE_NEW.getCode());

        //收货编号
        homecareQuickOrderEntity.setReceiveNo(orderSubmitQuickVo.getReceiveNo());

        //服务时间
        homecareQuickOrderEntity.setServerTime(orderSubmitQuickVo.getServerTime());

        //用户编号
        String token = orderSubmitQuickVo.getToken();
        String userName = JwtHelper.getUserName(token);
        homecareQuickOrderEntity.setUserNo(userName);

        //用户名字
        R r = userFeignService.getUserInfo(userName);
        if(r.getCode() == 200) {
            UserInfoVo userInfoVo = r.getData(new TypeReference<UserInfoVo>() {});
            homecareQuickOrderEntity.setUserName(userInfoVo.getNikeName());
        }

        //服务项编号
        homecareQuickOrderEntity.setPropertyNo(orderSubmitQuickVo.getPropertyNo());

        //总价
        homecareQuickOrderEntity.setTotalPrice(orderSubmitQuickVo.getTotalPrice());

        //优惠
        homecareQuickOrderEntity.setIntegral(orderSubmitQuickVo.getIntegral());
        homecareQuickOrderEntity.setIntegralDiscount(orderSubmitQuickVo.getIntegralDiscount());
        homecareQuickOrderEntity.setMemberPrice(orderSubmitQuickVo.getMemberPrice());
        //优惠后价格
        homecareQuickOrderEntity.setRealPrice(orderSubmitQuickVo.getRealPrice());

        //快捷订单名称
        homecareQuickOrderEntity.setQuickName(orderSubmitQuickVo.getQuickName());

        //订单时间
        homecareQuickOrderEntity.setCreateTime(new Date());
        homecareQuickOrderEntity.setUpdateTime(new Date());

        //保存订单
        this.save(homecareQuickOrderEntity);
    }


    /**
     * 订单确认页数据
     * @return 订单确认页数据vo
     */
    @Override
    public OrderConfirmVo confirmOrder(String id) {
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        QueryWrapper<QuickOrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",id);
        QuickOrderEntity orderEntity = this.getOne(wrapper);

        orderConfirmVo.setServerTime(orderEntity.getServerTime());
        //设置用户收货地址信息
        R r = userFeignService.getUserDefaultReceiveByUsername(orderEntity.getUserNo());
        UserReceiveVo userReceiveVo = r.getData(new TypeReference<UserReceiveVo>(){});
        orderConfirmVo.setUserReceiveVo(userReceiveVo);
        ServerItemEntity serverItem = serverItemService.getById(orderEntity.getPropertyNo());
        R userInfo = userFeignService.getUserInfo(orderEntity.getUserNo());
        UserInfoVo userInfoVo = userInfo.getData(new TypeReference<UserInfoVo>(){});
        orderConfirmVo.setGender(userInfoVo.getUserGender());

        //设置服务项信息
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
        consumptionEntityQueryWrapper.eq("consumer_site","居家康养");
        ConsumptionEntity consumptionEntity = consumptionService.getOne(consumptionEntityQueryWrapper);


        //会员优惠价格
        BigDecimal price = serverItem.getPrice().multiply(consumptionEntity.getConsumerDiscount());
        orderConfirmVo.setMemberPrice(serverItem.getPrice().subtract(price));

        //积分优惠价格
        BigDecimal multiply = new BigDecimal(consumptionEntity.getIntegralRate()).multiply(consumptionEntity.getConsumerBili());
        orderConfirmVo.setConsumerDiscount(multiply);
        orderConfirmVo.setIntegralRate(consumptionEntity.getIntegralRate());

        //真实价格
        BigDecimal realPrice = serverItem.getPrice().subtract(orderConfirmVo.getMemberPrice()).subtract(multiply);
        orderConfirmVo.setRealPrice(realPrice);
        return orderConfirmVo;
    }

    /**
     * 获取快捷订单数据
     * @param token token令牌
     * @return 快捷订单列表数据VO集合
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
//     * 获取快捷订单确认页数据
//     * @param id 快捷订单编号
//     * @return 确认订单页面数据
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