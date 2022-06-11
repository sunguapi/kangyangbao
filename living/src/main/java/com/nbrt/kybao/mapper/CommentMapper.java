package com.nbrt.kybao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbrt.kybao.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hjh
 * @date 2022/6/2 21:40
 */
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 添加评价-根据酒店id
     * @param comment
     * @return
     */
    @Transactional
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
}
