package com.nbrt.kybao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.entity.Children;
import com.nbrt.kybao.vo.ChildrenInfoVo;
import com.nbrt.kybao.vo.UserChildrenVo;

import java.util.List;

/**
 * @author sunjinbao
 * @date 2022/6/1
 */

public interface ChildrenService extends IService<Children> {
    Page<UserChildrenVo> searchChildrenById(String primaryUserNo, int pageNum, int pageSize);

//    ChildrenInfoVo searchUserInfoByChildrenId(String childrenId);
}
