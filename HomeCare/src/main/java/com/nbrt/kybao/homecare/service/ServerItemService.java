package com.nbrt.kybao.homecare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.homecare.entity.ServerItemEntity;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.vo.ServerItemDetailVo;
import com.nbrt.kybao.homecare.vo.severItem.ServerItemSaveVo;
import com.nbrt.kybao.homecare.vo.severItem.ServerItemUpdateVo;


import java.util.Map;

/**
 * 服务项
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
public interface ServerItemService extends IService<ServerItemEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //获取服务项详情页面数据
    ServerItemDetailVo getServerItemDetail(Long serverItemId);

    //保存服务项
    void saveSeverItem(com.nbrt.kybao.homecare.vo.severItem.ServerItemSaveVo serverItemVo);

    //删除服务信息
    void removeServerItemById(Long serverItemId);

    //修改服务项信息
    void updateServerItemById(com.nbrt.kybao.homecare.vo.severItem.ServerItemSaveVo serverItem);

    //查询服务项信息
    ServerItemUpdateVo getServerItemById(Long serverItemId);
}

