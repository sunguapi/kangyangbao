package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hjh
 * @date 2022/6/2 21:43
 */
@Mapper
@Repository
public interface RoomMapper extends BaseMapper<Room> {
    /**
     * 查询客房列表-该客房所属的酒店的id
     * @param whoseId 该客房所属的酒店的id
     * @return List<Room>
     */
    Page<Room> findRoomListByWhoseId(
            @Param("page") Page<Room> page,
            @Param("whoseId") int whoseId
    );

    /**
     * 根据id查询客房详情
     * @param id
     * @return
     */
    Room findRoomById(int id);


    /**
     * 添加客房
     * @param room
     * @return
     */
    @Transactional
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
     * 查询客房并分页
     */
    Page<Room> findRoomPage(
            @Param("page") Page<Room> page,
            @Param("roomName") String roomName,
            @Param("roomState") int roomState,
            @Param("whoseId") int whoseId
    );


    /**
     * 修改客房状态
     * @param id
     * @return
     */
    boolean updateRoomState(@Param("id") int id, @Param("roomState") int roomState);

    /**
     * 修改客房基本信息管理(后台)-根据id
     * @param id
     * @return
     */
    boolean updateRoomInformationById(
            @RequestParam("id") int id,
            @RequestParam("hotelProvince") String hotelProvince,
            @RequestParam("hotelCity") String hotelCity,
            @RequestParam("environment") String environment,
            @RequestParam("hotelName") String hotelName,
            @RequestParam("imageText") String imageText,
            @RequestParam("attributeLabel") String attributeLabel,
            @RequestParam("roomPicture") String roomPicture
    );
}
