package com.nbrt.kybao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.entity.*;
import com.nbrt.kybao.entity.dto.OrderDTO;
import com.nbrt.kybao.vo.SojournHotelVo;

import java.util.List;

public interface SojournService extends IService<Hotel> {


    /**
     * 大按键查询酒店-酒店类型&市&(搜索值)
     * @param hotelType 酒店类型(1田园 2公寓 3酒店)
     * @param hotelCity 市(酒店)
     * @param searchValue 搜索值
     * @return List<Hotel>
     */
    IPage<Hotel> findHotel(Integer hotelType,String hotelCity,String searchValue, Integer pageNumber, Integer pageSize);

    /**
     * 查询客房列表-该客房所属的酒店的id
     * @param whoseId 该客房所属的酒店的id
     * @return List<Room>
     */
    IPage<Room> findRoomListByWhoseId(int whoseId, Integer pageNumber, Integer pageSize);

    /**
     * (移动端)计算订单
     *
     */

    Order calculateOrder(Order order,int id,String token);
    //订单更新
    boolean updateOrder(Order order);


    /**
//     * 生成订单
//     */
//    boolean createOrder(Order order);

    /**
     * (移动端)预定成功页面-根据订单号
     * @param orderNo
     * @return
     */
    Order getOrder2(String orderNo);
    //修改订单状态:支付中→支付完成-根据订单号
    boolean updateOrderByOrderNo(String orderNo);
    //查询酒店信息-根据whoseid
    Hotel findHotelInformation(int whoseId);

    /**
     * (移动端)取消订单-根据订单号
     * @param orderNo
     * @return
     */
    boolean cancelOrder(String orderNo);

    /**
//     * 查显推荐酒店酒店(首页面)-最新更改为推荐的两个
//     * @param
//     * @return
//     */
    List<SojournHotelVo> getHotelByNewestRecommend();

    /**
     * 查显推荐酒店(旅居页面下方)-酒店类型、推荐
     * @param
     * @return
     */
    Page<SojournHotelVo> getHotelByRecommend(int hotelType, int recommend ,Integer pageNumber, Integer pageSize,String hotelCity,
                                             String hotelProvince);

    /**
     * 酒店推荐更改(后台)-根据id
     * @param hotel
     * @return
     */
    boolean updateHotelRecommendById(Hotel hotel);

    /**
     * 添加酒店
     * @param hotel
     * @return
     */
    boolean addHotel(Hotel hotel);

    /**
     * 删除酒店-根据id
     * @param id
     * @return
     */
    boolean deleteHotelById(int id);

    /**
     * 修改酒店信息-根据id
     * @param hotel
     * @return
     */
    boolean updateHotelById(Hotel hotel);

    /**
     * 查询酒店-根据id
     * @param id
     * @return
     */
    Hotel getHotelById(int id);

    /**
     * 查询酒店并分页(后台)-省市&(酒店名)
     */
    Page<Hotel> getHotelPage(String hotelProvince,String hotelCity,String hotelName, Integer pageNumber, Integer pageSize);

    /**
     * 添加客房
     * @param room
     * @return
     */
    boolean addRoom(Room room);

    /**
     * 删除客房-根据id
     * @param id
     * @return
     */
    boolean deleteRoomById(int id);

    /**
     * 修改客房-根据id
     * @param room
     * @return
     */
    boolean updateRoomById(Room room);

    /**
     * 根据id查询客房详情
     * @param id
     * @return
     */
    Room findRoomById(int id);

    /**
     * 查询客房并分页
     */
    Page<Room> findRoomPage(String roomName,int roomState,int whoseId,Integer pageNumber, Integer pageSize);

    /**
     * 修改客房状态
     * @param id
     * @return
     */
    boolean updateRoomState(int id,int roomState);
    //(web端)录入住客信息-根据订单号
    boolean addResidentInformation(String orderNo,String fourlong);

    //(web端)完成订单-2.支付完成→3.已完成-根据订单号
    boolean finishOrder(String orderNo);
    //(web端)查询住客信息-根据订单号
    Order findOrderResidentInformation(String orderNo);
    /**
     * 售价管理(后台)-根据id
     * @param id
     * @return
     */
    boolean updatePriceById(int id,double price,double memberPrice,double bargainPrice);

    /**
     * 修改客房基本信息管理(后台)-根据id
     * @param id
     * @return
     */
    boolean updateRoomInformationById(int id,String hotelProvince,String hotelCity,String environment,String hotelName,String imageText,String attributeLabel,String roomPicture);

    /**
     * (web端)订单部分：
     */
    //(web端)查询订单并分页-(酒店名)&(姓名)&(订单号)
    Page<Order> findOrderBy4(Integer pageNumber,Integer pageSize,String hotelName,String residentName,String orderNo,int state);
    //(web端)订单详情-订单编号
    Order OrderDetailByOrderNo(String orderNo);


    /**
     * 添加评价-根据酒店id
     * @param comment
     * @return
     */
    boolean addCommentByHotelId(Comment comment);

    /**
     * 查询该店所有评价-根据酒店id
     * @return List<Comment>
     */
    List<Comment> findAllComment(int idsHotelId);

    /**
     * 删除评价(后台)-根据评价id、酒店id
     * @param id
     * @return
     */
    boolean deleteCommentById(int id,int idsHotelId);

    void createOrder(Order order,String token,int id);
}