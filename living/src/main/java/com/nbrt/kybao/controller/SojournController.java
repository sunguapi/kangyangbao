package com.nbrt.kybao.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.*;
import com.nbrt.kybao.entity.dto.FindRoomPageDTO;
import com.nbrt.kybao.entity.dto.GetHotelPageDTO;
import com.nbrt.kybao.entity.dto.OrderDTO;
import com.nbrt.kybao.entity.dto.UpdateRoomStateDTO;
import com.nbrt.kybao.service.SojournService;
import com.nbrt.kybao.utils.AjaxResponse;
import com.nbrt.kybao.vo.SojournHotelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 旅居康养
 */
@CrossOrigin
@RestController
@RequestMapping("/sojourn")
@Api(tags = "旅居康养")
@Slf4j
public class SojournController {
    @Autowired
    private SojournService sojournService;


    /**
     * (移动端)旅居主页面大按键查询酒店部分：
     */
    @ApiOperation("(移动端)大按键查询酒店-酒店类型&市&(搜索值)")
    @GetMapping("/findHotelBy2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hotelType", value = "酒店类型(1田园 2公寓 3酒店)"),
            @ApiImplicitParam(name = "hotelCity", value = "市(酒店)"),
            @ApiImplicitParam(name = "searchValue", value = "搜索值")
    })
    public AjaxResponse<IPage<Hotel>> findHotel(Integer hotelType,String hotelCity,String searchValue,
                                               Integer pageNumber, Integer pageSize){
        IPage<Hotel> hotel = sojournService.findHotel(hotelType, hotelCity, searchValue, pageNumber, pageSize);
        return AjaxResponse.success(hotel);
    }

    /**
     * (移动端)旅居页面下的：
     */
    @ApiOperation("(移动端)查询客房列表-该客房所属的酒店的id")//大按键查询后所点击其中一酒店后弹出的酒店之下方的客房列表
    @PostMapping("/findRoomListByWhoseId")
    @ApiImplicitParams({@ApiImplicitParam(name = "whoseId", value = "该客房所属的酒店的id")})
    public AjaxResponse<IPage<Room>> findRoomListByWhoseId(int whoseId, Integer pageNumber, Integer pageSize){
        IPage<Room> page = sojournService.findRoomListByWhoseId(whoseId, pageNumber, pageSize);
        return AjaxResponse.success(page);
    }


    @ApiOperation("(移动端)订单费用计算")
    @PostMapping("/calculateOrder")
    public AjaxResponse calculateOrder(@RequestBody Order order,int id,String token) {
        return AjaxResponse.success(sojournService.calculateOrder(order,id,token));
    }
    @ApiOperation("移动端---生成订单")
    @PostMapping("/createOrder")
    public AjaxResponse createOrder(@RequestBody Order order, String token,int id) {
        sojournService.createOrder(order,token,id);
        return AjaxResponse.success();
    }

    @ApiOperation("(移动端)预定成功页面-根据订单号")
    @PostMapping("/getOrder2/{orderNo}")
    @ApiImplicitParams({@ApiImplicitParam(name = "orderNo", value = "订单编号")})
    public AjaxResponse getOrder2(@PathVariable("orderNo") String orderNo){
        return AjaxResponse.success(sojournService.getOrder2(orderNo));
    }

    @ApiOperation("(移动端)确认取消订单-根据订单号")
    @PostMapping("/cancelOrder/{orderNo}")
    @ApiImplicitParams({@ApiImplicitParam(name = "orderNo", value = "订单编号")})
    public AjaxResponse cancelOrder(@PathVariable("orderNo") String orderNo){
        if(sojournService.cancelOrder(orderNo)){
            return AjaxResponse.success("取消订单成功！");
        }
        return AjaxResponse.error("取消订单失败！");
    }




    /**
     * 首页面中的推荐：
     */
    @ApiOperation("(移动端)查显推荐酒店(首页面)-最新更改为推荐的两个")
    @PostMapping("/getHotelByNewestRecommend")
    public AjaxResponse<List<SojournHotelVo>> getHotelByNewestRecommend(){
        List<SojournHotelVo> list = sojournService.getHotelByNewestRecommend();
        return AjaxResponse.success(list);
    }

    /**
     * 旅居主页面-下面的推荐：
     * <List<Map<String,Object>>>:查询出的列表集合 可以不用整个实体，而显示SQL语句查询出的值的列表集合（为空的则不显示）
     */
    @ApiOperation("(移动端)查显推荐酒店(旅居页面下方)-酒店类型、推荐")
    @GetMapping("/getHotelByRecommend")
    @ApiImplicitParams({@ApiImplicitParam(name = "hotelType", value = "酒店类型(1田园 2公寓 3酒店)"),
                        @ApiImplicitParam(name = "recommend", value = "是否推荐")})
    public AjaxResponse<Page<SojournHotelVo>> getHotelByRecommend(
            int hotelType,
            int recommend,
            Integer pageNumber,
            Integer pageSize,
            String hotelCity,
            String hotelProvince
    ){
        log.info("====================>" + hotelType);
        Page<SojournHotelVo> list=sojournService.getHotelByRecommend(hotelType,recommend,pageNumber,pageSize,hotelCity,hotelProvince);
        return AjaxResponse.success(list);
    }


    /**
     * 后台酒店增删改查部分：
     */
    @ApiOperation("(web端)酒店推荐更改-根据id(只填id、recommend)")
    @PostMapping("/updateHotelRecommendById")
    public AjaxResponse updateHotelRecommendById(@RequestBody Hotel hotel){
        if(sojournService.updateHotelRecommendById(hotel)){
            return AjaxResponse.success(null,"更改成功！");
        }
        return AjaxResponse.error("更改失败！");
    }

    @ApiOperation("(web端)添加酒店")
    @PostMapping("/addHotel")
    public AjaxResponse addHotel(@RequestBody Hotel hotel){
        if(sojournService.addHotel(hotel)){
            return AjaxResponse.success(null,"添加成功！");
        }
        return AjaxResponse.error("添加失败！");
    }

    @ApiOperation("(web端)删除酒店-根据id")
    @GetMapping("/deleteHotelById/{id}")//要加/{id}
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "酒店id")})
    public AjaxResponse deleteHotelById(@PathVariable("id") int id){
        if(sojournService.deleteHotelById(id)){
            return AjaxResponse.success(null,"删除该酒店成功！");
        }
        return AjaxResponse.error("删除该酒店失败！");
    }

    @ApiOperation("(web端)修改酒店信息-根据id")
    @PostMapping("/updateHotelById")
    public AjaxResponse updateHotelById(@RequestBody Hotel hotel){
        if(sojournService.updateHotelById(hotel)){
            return AjaxResponse.success(null,"修改酒店信息成功！");
        }
        return AjaxResponse.error("修改酒店信息失败！");
    }

    @ApiOperation("(web端)查询酒店-根据id")
    @GetMapping("/getHotelById/{id}")//要加/{id} 因为下行的注解
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "酒店id")})
    public AjaxResponse getHotelById(@PathVariable("id") int id){
        return AjaxResponse.success(sojournService.getHotelById(id));
    }

    @ApiOperation("(web端)查询酒店并分页-(省市)&(酒店名)")
    @PostMapping("/getHotelPage")
    public AjaxResponse<Page<Hotel>> getHotelPage(@RequestBody GetHotelPageDTO getHotelPageDTO, Integer pageNumber, Integer pageSize) {//前端喜爱的条件查询方式
        Page<Hotel> list=sojournService.getHotelPage(
                getHotelPageDTO.getHotelProvince(),
                getHotelPageDTO.getHotelCity(),
                getHotelPageDTO.getHotelName(),
                pageNumber,pageSize
        );
        return AjaxResponse.success(list);
    }


    /**
     * 客房增删改查部分：
     */
    @ApiOperation("(web端)添加客房")
    @PostMapping("/addRoom")
    public AjaxResponse addRoom(@RequestBody Room room){
        if(sojournService.addRoom(room)){
            return AjaxResponse.success(null,"添加成功！");
        }
        return AjaxResponse.error("添加失败！");
    }

    @ApiOperation("(web端)删除客房-根据id")
    @GetMapping("/deleteRoomById/{id}")//要加/{id}
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "客房id")})
    public AjaxResponse deleteRoomById(@PathVariable("id") int id){
        if(sojournService.deleteRoomById(id)){
            return AjaxResponse.success(null,"删除成功！");
        }
        return AjaxResponse.error("删除失败！");
    }

    @ApiOperation("(web端)修改客房信息-根据id")
    @PostMapping("/updateRoomById")
    public AjaxResponse updateRoomById(@RequestBody Room room){
        if(sojournService.updateRoomById(room)){
            return AjaxResponse.success(null,"修改成功！");
        }
        return AjaxResponse.error("修改失败！");
    }

    @ApiOperation("查询客房详情-根据id")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "客房id")})
    @GetMapping("/findRoomById/{id}")
    public AjaxResponse<Room> findRoomById(@PathVariable int id){
        return AjaxResponse.success(sojournService.findRoomById(id));
    }

    @ApiOperation("(web端)查询客房并分页")
    @PostMapping("/findRoomPage")
    public AjaxResponse<Page<Room>> findRoomPage(@RequestBody FindRoomPageDTO findRoomPageDTO, Integer pageNumber, Integer pageSize){
        Page<Room> page=sojournService.findRoomPage(
                findRoomPageDTO.getRoomName(),
                findRoomPageDTO.getRoomState(),
                findRoomPageDTO.getWhoseId(),
                pageNumber,
                pageSize
        );
        return AjaxResponse.success(page);
    }

    @ApiOperation("(web端)修改客房状态")
    @PostMapping("/updateRoomState")
    public AjaxResponse updateRoomState(@RequestBody UpdateRoomStateDTO updateRoomStateDTO){
        if(sojournService.updateRoomState(updateRoomStateDTO.getId(),updateRoomStateDTO.getRoomState())){
            return AjaxResponse.success(null,"修改成功！");
        }
        return AjaxResponse.error("修改失败！");
    }



    @ApiOperation("(web端)录入住客信息-根据订单号")
    @PostMapping("/addResidentInformation")

    public AjaxResponse<Order> addResidentInformation(@RequestBody Order order) {   //此方式在把数据传给（后端）时，可传送2M的内容，不易丢失
        if(sojournService.addResidentInformation(order.getOrderNo(),order.getFourlong())){
            return AjaxResponse.success(null,"成功！");}
        return AjaxResponse.error("失败！");}

    @ApiOperation("(web端)完成订单(住完走人)-2.支付完成→3.已完成-根据订单号")
    @PostMapping("/finishOrder/{orderNo}")
    @ApiImplicitParams({@ApiImplicitParam(name = "orderNo", value = "订单编号")})
    public AjaxResponse finishOrder(@PathVariable("orderNo") String orderNo){
        if(sojournService.finishOrder(orderNo)){
            return AjaxResponse.success(null,"成功！");}
        return AjaxResponse.error("失败！");}

    @ApiOperation("(web端)查询住客信息-根据订单号")
    @PostMapping("/findOrderResidentInformation/{orderNo}")
    @ApiImplicitParams({@ApiImplicitParam(name = "orderNo", value = "订单编号")})
    public AjaxResponse findOrderResidentInformation(@PathVariable("orderNo") String orderNo){
        return AjaxResponse.success(sojournService.findOrderResidentInformation(orderNo));}



    /**
     * 后台修改售价管理部分：
     */
//    @ApiOperation("(web端)修改售价管理-根据id")
//    @PostMapping("/updatePriceById")
//    public AjaxResponse updatePriceById(@RequestBody UpdatePriceByIdDTO updatePriceByIdDTO){
//        if(sojournService.updatePriceById(updatePriceByIdDTO.getId(),updatePriceByIdDTO.getPrice(),updatePriceByIdDTO.getMemberPrice(),updatePriceByIdDTO.getBargainPrice())){
//            return AjaxResponse.success(null,"修改售价成功！");
//        }
//        return AjaxResponse.error("修改售价失败！");
//    }



    /**
     * 后台修改客房基本信息管理部分：
     */
    @ApiOperation("(未知)修改客房基本信息管理-根据id")
    @PostMapping("/updateRoomInformationById")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "酒店id"),//请求，需要输入输入框的参数
            @ApiImplicitParam(name = "hotelProvince", value = "省(酒店)"),
            @ApiImplicitParam(name = "hotelCity", value = "市(酒店)"),
            @ApiImplicitParam(name = "environment", value = "所属地产环境"),
            @ApiImplicitParam(name = "hotelName", value = "酒店名称"),
            @ApiImplicitParam(name = "imageText", value = "图文介绍"),
            @ApiImplicitParam(name = "attributeLabel", value = "属性标签"),
            @ApiImplicitParam(name = "roomPicture", value = "房间图片")})
    public AjaxResponse updateRoomInformationById(@RequestParam("id") int id,//请求，需要输入输入框的参数
                                                  @RequestParam("hotelProvince") String hotelProvince,
                                                  @RequestParam("hotelCity") String hotelCity,
                                                  @RequestParam("environment") String environment,
                                                  @RequestParam("hotelName") String hotelName,
                                                  @RequestParam("imageText") String imageText,
                                                  @RequestParam("attributeLabel") String attributeLabel,
                                                  @RequestParam("roomPicture") String roomPicture){
        if(sojournService.updateRoomInformationById(id,hotelProvince,hotelCity,environment,hotelName,imageText,attributeLabel,roomPicture)){
            return AjaxResponse.success(null,"修改客房信息成功！");}
        return AjaxResponse.error("修改客房信息失败！");}
//    public AjaxResponse updateRoomInformationById(@RequestBody ){
//        if(sojournService.updateRoomInformationById()){
//            return AjaxResponse.success(null,"修改客房信息成功！");
//        }
//        return AjaxResponse.error("修改客房信息失败！");
//    }


    /**
     * (web端)订单管理部分：
     */
    @ApiOperation("(web端)查询订单并分页-(酒店名)&(姓名)&(订单号)&(订单状态)")
    @GetMapping("/findOrderBy4")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")})
    public AjaxResponse<Page<Order>> findOrderBy4(Integer pageNumber,Integer pageSize,String hotelName,String residentName,String orderNo,Integer state){
        Page<Order> page=sojournService.findOrderBy4(pageNumber,pageSize,hotelName,residentName,orderNo,state);
        return AjaxResponse.success(page);
    }
    @ApiOperation("(web端)订单详情-订单编号")
    @GetMapping("/OrderDetailByOrderNo/{orderNo}")
    @ApiImplicitParams({@ApiImplicitParam(name = "orderNo", value = "酒店id")})
    public AjaxResponse OrderDetailByOrderNo(@PathVariable("orderNo") String orderNo){
        return AjaxResponse.success(sojournService.OrderDetailByOrderNo(orderNo));
    }



    /**
     * 酒店评价部分：
     * 1.暂未加入评价的用户昵称
     * 2.添加评价的酒店id暂未与酒店表中的酒店id相关联，不知能否胜任
     * 3.（查看该店所有评价，如果酒店已被删除，应该不影响）
     */
//    @ApiOperation("(移动端)添加评价-根据酒店id")
//    @PostMapping("/addCommentByHotelId")
//    public AjaxResponse addCommentByHotelId(@RequestBody Comment comment){
//        if(sojournService.addCommentByHotelId(comment)){
//            return AjaxResponse.success(null,"添加成功！");
//        }
//        return AjaxResponse.error("添加失败！");
//    }
//
//    @ApiOperation("(移动端)查询该店所有评价-根据酒店id")
//    @GetMapping("/findAllComment")
////    @ApiImplicitParams({@ApiImplicitParam(name = "idsHotelId", value = "酒店id")})//此处注解应为swagger接口文档下 （一样的1）
//    public AjaxResponse<List<Comment>> findAllComment(@RequestParam("酒店id") int idsHotelId){//此处注解应为需要输入参数部分的 （一样的2）
//        return AjaxResponse.success(sojournService.findAllComment(idsHotelId));
//    }
//
//    @ApiOperation("(web端)删除评价-根据评价id、酒店id")
//    @PostMapping("/deleteCommentById")
//    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "评价id"),
//            @ApiImplicitParam(name = "idsHotelId", value = "该评价所属酒店的酒店id")})
//    public AjaxResponse deleteCommentById(int id,int idsHotelId){
//        if(sojournService.deleteCommentById(id,idsHotelId)){
//            return AjaxResponse.success(null,"删除该评价成功！");
//        }
//        return AjaxResponse.error("删除该评价失败！");
//    }



}
