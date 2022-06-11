package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.Hotel;
import com.nbrt.kybao.vo.SojournHotelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hjh
 * @date 2022/6/2 21:41
 */
@Mapper
@Repository
public interface HotelMapper extends BaseMapper<Hotel> {
    /**
     * 大按键查询酒店-酒店类型&市&(搜索值)
     *
     * @param hotelType   酒店类型(1田园 2公寓 3酒店)
     * @param hotelCity   市(酒店)
     * @param searchValue 搜索值
     * @return List<Hotel>
     */
    Page<Hotel> findHotel(
            @Param("page") Page<Hotel> page,
            @Param("hotelType") Integer hotelType,
            @Param("hotelCity") String hotelCity,
            @Param("searchValue") String searchValue
    );

    //查询酒店信息-根据whoseid
    Hotel findHotelInformation(int whoseId);


    /**
     * 查显推荐酒店(首页面)-最新更改为推荐的两个
     * @param
     * @return
     */
//    Page<SojournHotelVo> getHotelByNewestRecommend(@Param("page") Page<SojournHotelVo> page);




    /**
     * 酒店推荐更改(后台)-根据id
     *
     * @param hotel
     * @return
     */
    boolean updateHotelRecommendById(Hotel hotel);

    /**
     * 添加酒店
     *
     * @param hotel
     * @return
     */
    @Transactional
    boolean addHotel(Hotel hotel);

    /**
     * 删除酒店-根据id
     *
     * @param id
     * @return
     */
    boolean deleteHotelById(int id);

    /**
     * 修改酒店信息-根据id
     *
     * @param hotel
     * @return
     */
    @Transactional
    boolean updateHotelById(Hotel hotel);

    /**
     * 查询酒店-根据id
     *
     * @param id
     * @return
     */
    Hotel getHotelById(int id);

    /**
     * 查询酒店并分页(后台)-省市&(酒店名)
     */
    Page<Hotel> getHotelPage(
            @Param("page") Page<Hotel> page,
            @Param("hotelProvince") String hotelProvince,
            @Param("hotelCity") String hotelCity,
            @Param("hotelName") String hotelName);


    /**
     * 售价管理(后台)-根据id
     *
     * @param id
     * @return
     */
    boolean updatePriceById(@Param("id") int id, @Param("price") double price, @Param("memberPrice") double memberPrice, @Param("bargainPrice") double bargainPrice);

    /**
     * 获取首页旅居康养推荐位---酒店信息
     * @return
     */
    List<SojournHotelVo> getHotelByNewestRecommend();

    /**
     * 查显推荐酒店(旅居页面下方)-酒店类型、推荐
     *
     * @param hotelType,recommend
     * @return
     */
    Page<SojournHotelVo> getHotelByRecommend(@Param("page") Page<SojournHotelVo> page, @Param("hotelType") int hotelType, @Param("recommend") int recommend, @Param("hotelCity") String hotelCity, @Param("hotelProvince") String hotelProvince);

    Page<SojournHotelVo> setHotelByNewestRecommend(@Param("page") Page<SojournHotelVo> page);

    Hotel selectByWhoseId(@Param("whoseId") int whoseId);
}
