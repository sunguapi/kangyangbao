package com.nbrt.kybao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.entity.*;
import com.nbrt.kybao.entity.dto.OrderDTO;
import com.nbrt.kybao.mapper.*;
import com.nbrt.kybao.service.SojournService;
import com.nbrt.kybao.utils.JwtHelper;
import com.nbrt.kybao.utils.RandomStringUtil;
import com.nbrt.kybao.vo.SojournHotelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SojournServiceImpl extends ServiceImpl<SojournMapper,Hotel> implements SojournService {

    @Autowired
    private ConsumptionMapper consumptionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SojournMapper sojournMapper;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CommentMapper commentMapper;


    /**
     * 大按键查询酒店-酒店类型&市&(搜索值)
     * @param hotelType 酒店类型(1田园 2公寓 3酒店)
     * @param hotelCity 市(酒店)
     * @param searchValue 搜索值
     * @return List<Hotel>
     */
    @Override
    public IPage<Hotel> findHotel(Integer hotelType,String hotelCity,String searchValue, Integer pageNumber, Integer pageSize) {

        Page<Hotel> page =new Page<>(pageNumber,pageSize);

        List<Hotel> hotel = hotelMapper.findHotel(page, hotelType, hotelCity, searchValue).getRecords();

        for (Hotel hotels : hotel) {
            Integer id = hotels.getId();
            QueryWrapper<Room> wrapper = new QueryWrapper<Room>().eq("whose_id", id);
            List<Room> list = roomMapper.selectList(wrapper);
            double bargainPriceTemp = 0.00;

            int roomIdTemp = 0;
            for (Room room : list) {
                double bargainPrice = room.getBargainPrice().doubleValue();
                int roomId = room.getId();
                if(bargainPriceTemp == 0.00) {
                    bargainPriceTemp = bargainPrice;
                    roomIdTemp = roomId;

                }
                if(bargainPrice < bargainPriceTemp) {
                    bargainPriceTemp = bargainPrice;
                    roomIdTemp = roomId;
                }
            }
            QueryWrapper<Room> wrapper1 = new QueryWrapper<Room>().eq("id", roomIdTemp);

            Room room = roomMapper.selectOne(wrapper1);
            if(room != null) {
                hotels.setMemberPrice(room.getBargainPrice().doubleValue());
                hotels.setPrice(room.getPrice().doubleValue());
            }
        }

        return page.setRecords(hotel);

    }



    /**
     * 查询客房列表-该客房所属的酒店的id
     * @param whoseId 该客房所属的酒店的id
     * @return List<Room>
     */
    @Override
    public IPage<Room> findRoomListByWhoseId(int whoseId, Integer pageNumber, Integer pageSize) {
        Page<Room> page=new Page<>(pageNumber,pageSize);
        Page<Room> list = roomMapper.findRoomListByWhoseId(page, whoseId);
        return list;
    }

    /**
     * (移动端)计算订单
     */
    @Transactional
    @Override
    public Order calculateOrder(Order order,int id,String token) {//,int whoseId,Hotel hotel,int id2

        log.info("=======================>" +order);
        // id = 客房id
        Room room = roomMapper.selectById(id);

        BigDecimal roomPrice = room.getPrice();//客房普价
        BigDecimal roomBargainPrice = room.getBargainPrice();//客房特价
        BigDecimal roomMealFee = room.getMealFee();//餐费
//        String roomLabel = room.getLabel();//客房标签
        int whoseId = room.getWhoseId();//该客房所属的酒店的id

//        Hotel hotel = hotelMapper.selectById(whoseId);
//        String hotelName = hotel.getHotelName();//酒店名
//        String hotelTelephone = hotel.getHotelTelephone();//酒店电话

        //取前端传来的参数
        int days = order.getDays();//天数
        int roomNum = order.getRoomNum();//预定房间数量
        int residentNum = order.getResidentNum();//入住人数

        String userName = JwtHelper.getUserName(token);
        QueryWrapper<User> user_no = new QueryWrapper<User>().eq("user_no", userName);
        User user = userMapper.selectOne(user_no);


        QueryWrapper<Consumption> wrapper = new QueryWrapper<>();
        wrapper.eq("user_grade",user.getUserGrade());
        wrapper.eq("consumer_site","旅居康养");
        Consumption consumptionEntity = consumptionMapper.selectOne(wrapper);

        // 赋值房费
        if(roomBargainPrice != null) {
            order.setRoomFee(roomBargainPrice.doubleValue() * roomNum * days);//房
        }else {
            order.setRoomFee(roomPrice.doubleValue() * roomNum * days);//房费
        }

        // 设置餐费
        order.setMealFee(roomMealFee.doubleValue() * residentNum * days);
        //房费信息
        order.setExpenseInformation(order.getRoomFee() + order.getMealFee());


        // 会员优惠
        double totalFee = order.getExpenseInformation();
        BigDecimal consumerDiscount =consumptionEntity.getConsumerDiscount();
        DecimalFormat df = new DecimalFormat("######0.00");
        String format = df.format(totalFee * (1-consumerDiscount.doubleValue()));
        double doubleMemberBenefit = Double.valueOf(format).doubleValue();
        order.setMemberBenefit(doubleMemberBenefit);

        // 积分抵扣
        BigDecimal consumerBili = consumptionEntity.getConsumerBili();
        double v = consumerBili.doubleValue() * consumptionEntity.getIntegralRate().doubleValue() * order.getExpenseInformation();
        // 订单抵扣积分与用户现有积分进行判断 ，如果订单抵扣积分大于用户现有积分，就用用户现有积分进行抵扣
        // 如果订单积分小于用户现有积分就用订单积分进行抵扣
        if(v > user.getUserIntegral()) {
            order.setIntegralDeduct(user.getUserIntegral());

            //赋值积分价格
            int userIntegral = user.getUserIntegral(); //积分
            double IntegralRate = consumptionEntity.getIntegralRate().doubleValue();//兑换比例
            String IntegralDeductPriceft = df.format(userIntegral/IntegralRate);
            double doubleIntegralDeductPrice= Double.valueOf(IntegralDeductPriceft).doubleValue();
            order.setIntegralDeductPrice(doubleIntegralDeductPrice);
        }else {
            order.setIntegralDeduct(v);

            //赋值积分价格
            double IntegralRate = consumptionEntity.getIntegralRate().doubleValue();//兑换比例
            String doubleIntegralDeductPriceft = df.format(v/IntegralRate);
            double doubleIntegralDeductPrice= Double.valueOf(doubleIntegralDeductPriceft).doubleValue();
            order.setIntegralDeductPrice(doubleIntegralDeductPrice);
        }

        // 总价
        String TotalFeeformat = df.format(order.getExpenseInformation() - order.getMemberBenefit() - order.getIntegralDeductPrice());
        double doubleTotalFee = Double.valueOf(TotalFeeformat).doubleValue();
        order.setTotalFee(doubleTotalFee);
        // 优惠价
        String DeductFeeformat = df.format(order.getMemberBenefit() + order.getIntegralDeductPrice());
        double doubleDeductFee = Double.valueOf(DeductFeeformat).doubleValue();
        order.setDeductFee(doubleDeductFee);

        return order;
    }
    @Override
    //订单更新
    public boolean updateOrder(Order order) { return orderMapper.updateOrder(order); }


    /**
     * (移动端)预定成功页面-根据订单号
     * @param orderNo
     * @return
     */
    @Override
    public Order getOrder2(String orderNo) {
        orderMapper.updateOrderByOrderNo(orderNo);
        return orderMapper.getOrder2(orderNo); }
        @Override
    //修改订单状态:支付中→支付完成-根据订单号
    public boolean updateOrderByOrderNo(String orderNo) {
        return orderMapper.updateOrderByOrderNo(orderNo);
    }
    @Override
    //查询酒店信息-根据whoseid
    public Hotel findHotelInformation(int whoseId) { return hotelMapper.findHotelInformation(whoseId); }

    /**
     * (移动端)取消订单-根据订单号
     * @param orderNo
     * @return
     */
    @Override
    public boolean cancelOrder(String orderNo) { return orderMapper.cancelOrder(orderNo); }

    /**
     * 查显推荐酒店(首页面)-最新更改为推荐的两个
     *

     * @return
     */
    @Override
    public List<SojournHotelVo> getHotelByNewestRecommend() {

        List<SojournHotelVo> hotel = hotelMapper.getHotelByNewestRecommend();

        return setHotelRecommend(hotel);
    }



    /**
     * 查显推荐酒店(旅居页面下方)-酒店类型、推荐
     * @param hotelType,recommend
     * @return
     */
    @Override
    public Page<SojournHotelVo> getHotelByRecommend(int hotelType, int recommend, Integer pageNumber, Integer pageSize,String hotelCity,
                                                    String hotelProvince) {
        Page<SojournHotelVo> page=new Page<>(pageNumber,pageSize);

        List<SojournHotelVo> hotel = hotelMapper.getHotelByRecommend(page, hotelType, recommend, hotelCity, hotelProvince).getRecords();


        return page.setRecords(setHotelRecommend(hotel));

    }

    /**
     * 设置酒店推荐信息
     * @param hotel
     * @return
     */
    public List<SojournHotelVo> setHotelRecommend(List<SojournHotelVo> hotel) {
        for (SojournHotelVo hotels : hotel) {
            Integer id = hotels.getId();
            QueryWrapper<Room> wrapper = new QueryWrapper<Room>().eq("whose_id", id);
            List<Room> list = roomMapper.selectList(wrapper);
            double bargainPriceTemp = 0.00;

            int roomIdTemp = 0;
            for (Room room : list) {
                double bargainPrice = room.getBargainPrice().doubleValue();
                int roomId = room.getId();
                if(bargainPriceTemp == 0.00) {
                    bargainPriceTemp = bargainPrice;
                    roomIdTemp = roomId;

                }
                if(bargainPrice < bargainPriceTemp) {
                    bargainPriceTemp = bargainPrice;
                    roomIdTemp = roomId;
                }
            }
            QueryWrapper<Room> wrapper1 = new QueryWrapper<Room>().eq("id", roomIdTemp);

            Room room = roomMapper.selectOne(wrapper1);
            if(room != null) {
                hotels.setMemberPrice(room.getBargainPrice().doubleValue());
                hotels.setPrice(room.getPrice().doubleValue());
            }
        }
        return hotel;
    }

    /**
     * 酒店推荐更改(后台)-根据id
     * @param hotel
     * @return
     */
    @Override
    public boolean updateHotelRecommendById(Hotel hotel) {
        return hotelMapper.updateHotelRecommendById(hotel);
    }

    /**
     * 添加酒店
     * @param hotel
     * @return
     */
    @Override
    public boolean addHotel(Hotel hotel) {
        String hotelProvince = hotel.getHotelProvince();
        if(hotelProvince.equals("北京市") || hotelProvince.equals("天津市") || hotelProvince.equals("上海市")
                || hotelProvince.equals("深圳市") || hotelProvince.equals("重庆市")
        ) {
            hotel.setHotelCity(hotelProvince);
        }
        return hotelMapper.addHotel(hotel);
    }

    /**
     * 删除酒店-根据id
     * @param id
     * @return
     */
    @Override
    public boolean deleteHotelById(int id) {
        return hotelMapper.deleteHotelById(id);
    }

    /**
     * 修改酒店信息-根据id
     * @param hotel
     * @return
     */
    @Override
    public boolean updateHotelById(Hotel hotel) {
        String hotelProvince = hotel.getHotelProvince();
        if(hotelProvince.equals("北京市") || hotelProvince.equals("天津市") || hotelProvince.equals("上海市")
                || hotelProvince.equals("深圳市") || hotelProvince.equals("重庆市")
        ) {
            hotel.setHotelCity(hotelProvince);
        }

        return hotelMapper.updateHotelById(hotel);
    }

    /**
     * 查询酒店-根据id
     * @param id
     * @return
     */
    @Override
    public Hotel getHotelById(int id) {

        return hotelMapper.getHotelById(id);
    }

    /**
     * 查询酒店并分页(后台)-省市&(酒店名)
     */
    @Override
    public Page<Hotel> getHotelPage(String hotelProvince,String hotelCity,String hotelName, Integer pageNumber, Integer pageSize) {
        Page<Hotel> page=new Page<>(pageNumber, pageSize);
        return hotelMapper.getHotelPage(page,hotelProvince, hotelCity, hotelName);
    }


    /**
     * 添加客房
     * @param room
     * @return
     */
    @Override
    public boolean addRoom(Room room) {
        return roomMapper.addRoom(room);
    }

    /**
     * 删除客房-根据id
     * @param id
     * @return
     */
    @Override
    public boolean deleteRoomById(int id) {
        return roomMapper.deleteRoomById(id);
    }

    /**
     * 修改客房-根据id
     * @param room
     * @return
     */
    @Override
    public boolean updateRoomById(Room room) {
        return roomMapper.updateRoomById(room);
    }

    /**
     * 查询客房并分页
     */
    @Override
    public Page<Room> findRoomPage(String roomName,int roomState,int whoseId, Integer pageNumber, Integer pageSize) {
        Page<Room> page=new Page<>(pageNumber,pageSize);
        return roomMapper.findRoomPage(page,roomName,roomState,whoseId);
    }

    /**
     * 根据id查询客房详情
     * @param id
     * @return
     */
    @Override
    public Room findRoomById(int id){
        return roomMapper.findRoomById(id);
    }

    /**
     * 修改客房状态
     * @param id
     * @return
     */
    @Override
    public boolean updateRoomState(int id,int roomState) {
        return roomMapper.updateRoomState(id,roomState);
    }


    //(web端)录入住客信息-根据订单号
    @Override
    public boolean addResidentInformation(String orderNo,String fourlong) {
        return orderMapper.addResidentInformation(orderNo,fourlong);
    }

    //(web端)完成订单-2.支付完成→3.已完成-根据订单号
    @Override
    public boolean finishOrder(String orderNo) {
        return orderMapper.finishOrder(orderNo);
    }

    //(web端)查询住客信息-根据订单号
    @Override
    public Order findOrderResidentInformation(String orderNo) {
        return orderMapper.findOrderResidentInformation(orderNo);
    }
    /**
     * 售价管理(后台)-根据id
     * @param id
     * @return
     */
    @Override
    public boolean updatePriceById(int id,double price,double memberPrice,double bargainPrice) {
        return hotelMapper.updatePriceById(id,price,memberPrice,bargainPrice);
    }

    /**
     * 修改客房基本信息管理(后台)-根据id
     * @param id
     * @return
     */
    @Override
    public boolean updateRoomInformationById(int id,String hotelProvince,String hotelCity,String environment,String hotelName,String imageText,String attributeLabel,String roomPicture) {
        return roomMapper.updateRoomInformationById(id,hotelProvince,hotelCity,environment,hotelName,imageText,attributeLabel,roomPicture);
    }

    //(web端)查询订单并分页-(酒店名)&(姓名)&(订单号)
    @Override
    public Page<Order> findOrderBy4(Integer pageNumber,Integer pageSize,String hotelName,String residentName,String orderNo,int state) {
        Page<Order> page=new Page<>(pageNumber,pageSize);
        //查询所有
        return orderMapper.findOrderBy4(page,hotelName,residentName,orderNo,state);

    }

    //(web端)订单详情-订单编号
    @Override
    public Order OrderDetailByOrderNo(String orderNo) {
        return orderMapper.OrderDetailByOrderNo(orderNo);
    }



    /**
     * 添加评价-根据酒店id
     * @param
     * @return
     */
    @Override
    public boolean addCommentByHotelId(Comment comment) {
        return commentMapper.addCommentByHotelId(comment);
    }

    /**
     * 查询该店所有评价-根据酒店id
     * @return List<Comment>
     */
    @Override
    public List<Comment> findAllComment(int idsHotelId) {
        return commentMapper.findAllComment(idsHotelId);
    }

    /**
     * 删除评价(后台)-根据评价id、酒店id
     * @param id
     * @return
     */
    @Override
    public boolean deleteCommentById(int id,int idsHotelId) {
        return commentMapper.deleteCommentById(id,idsHotelId);
    }


    @Override
    public void createOrder(Order order, String token,int id) {
        if(token == null) {
            log.info("============token===============");
        }
        String userNo = JwtHelper.getUserName(token);
        Room room = roomMapper.selectById(id);


        String roomLabel = room.getLabel();
        int whoseId = room.getWhoseId();

        Hotel hotel = hotelMapper.selectByWhoseId(whoseId);
        String hotelName = hotel.getHotelName();
        String hotelTelephone = hotel.getHotelTelephone();


        Integer days = order.getDays();
        Integer roomNum = order.getRoomNum();
        Integer residentNum = order.getResidentNum();


        order.setUserNo(userNo);

        order.setOrderNo(RandomStringUtil.generateOrderNo());//获取随机订单编号
        order.setState(1);//提交订单后状态变为1.支付中

        order.setLabel(roomLabel);//标签
        order.setHotelName(hotelName);//存入酒店名
        order.setHotelPhone(hotelTelephone);//存入酒店电话
        order.setDays(days);
        order.setRoomNum(roomNum);
        order.setResidentNum(residentNum);

        orderMapper.insert(order);
    }

}
