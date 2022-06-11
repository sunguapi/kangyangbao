package com.nbrt.kybao.homecare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.homecare.entity.ServerEntity;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.vo.ServerItemSaveVo;
import com.nbrt.kybao.homecare.vo.ServerVo;
import com.nbrt.kybao.homecare.vo.server.ServerChangeVo;


import java.util.List;
import java.util.Map;

/**
 * 服务
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
public interface ServerService extends IService<ServerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //查询服务列表页面数据
    ServerVo getServerData(Long catItemId);

    //切换服务
    ServerChangeVo changeServer(Long serverId, Long catItemId);

    //排序切换
    List<ServerItemSaveVo> orderChange(Long serverId, Long orderNum);

}

