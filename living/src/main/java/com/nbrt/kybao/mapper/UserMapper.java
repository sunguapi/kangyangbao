package com.nbrt.kybao.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.entity.Order;
import com.nbrt.kybao.entity.User;
import com.nbrt.kybao.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户信息
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

//    Page<ShipAddress> searchUserAddress(
//            @Param("page") Page<ShipAddress> page,
//            @Param("id") int id
//    );

    List<UserVo> searchUserByName(String userName, String nickName);

    Page<UserVo> selectPageList(
            @Param("page") Page<UserVo> page,
            @Param("userName") String userName,
            @Param("nickName") String nickName
    );
}
