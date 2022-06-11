package com.nbrt.kybao.user.service.impl;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.nbrt.kybao.user.utils.JwtHelper;
import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.utils.Query;
import com.nbrt.kybao.user.dao.ReceiveDao;
import com.nbrt.kybao.user.entity.ReceiveEntity;
import com.nbrt.kybao.user.service.ReceiveService;
import com.nbrt.kybao.user.vo.UserReceiveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("receiveService")
public class ReceiveServiceImpl extends ServiceImpl<ReceiveDao, ReceiveEntity> implements ReceiveService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReceiveEntity> page = this.page(
                new Query<ReceiveEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    /**
     * 添加收获地址
     * @param userReceiveVo 添加收货地址页面
     */
    @Override
    public void saveReceive(UserReceiveVo userReceiveVo) {
        ReceiveEntity receiveEntity = new ReceiveEntity();
        String timeId = IdWorker.getIdStr();
        BeanUtils.copyProperties(userReceiveVo,receiveEntity);
        receiveEntity.setReceiveNo(timeId);
        receiveEntity.setCreateTime(new Date());
        receiveEntity.setUpdateTime(new Date());
        this.save(receiveEntity);
    }

    /**
     * 获取收货地址列表
     * @param token token令牌
     * @return 返回该用户下的所有的收货地址列表
     */
    @Override
    public List<UserReceiveVo> getReceiveList(String token) {
        QueryWrapper<ReceiveEntity> wrapper = new QueryWrapper<>();
        String userName = JwtHelper.getUserName(token);
        wrapper.eq("user_no",userName);
        List<ReceiveEntity> receiveEntities = this.list(wrapper);
        List<UserReceiveVo> userReceiveVos = receiveEntities.stream().map(item -> {
            UserReceiveVo userReceiveVo = new UserReceiveVo();
            BeanUtils.copyProperties(item, userReceiveVo);
            return userReceiveVo;
        }).collect(Collectors.toList());
        return userReceiveVos;
    }

    /**
     * 获取用户默认收货地址
     * @param username 用户编号
     */
    @Override
    public UserReceiveVo getUserDefaultReceiveByUsername(String username) {
        QueryWrapper<ReceiveEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_no",username);
        List<ReceiveEntity> receiveEntities = this.list(wrapper);
        UserReceiveVo userReceiveVo = new UserReceiveVo();
        for (ReceiveEntity receiveEntity : receiveEntities) {
            if(receiveEntity.getDefaultOn() == 1 && receiveEntity.getDeleted() == 0) {
                BeanUtils.copyProperties(receiveEntity,userReceiveVo);
                break;
            }
        }
        return userReceiveVo;
    }

    /**
     * 地址信息修改
     * @param userReceiveVo 收货地址信息
     */
    @Override
    public void updateReceive(UserReceiveVo userReceiveVo) {
        QueryWrapper<ReceiveEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_no",userReceiveVo.getReceiveNo());
        ReceiveEntity receiveEntity = new ReceiveEntity();
        BeanUtils.copyProperties(userReceiveVo,receiveEntity);
        receiveEntity.setUpdateTime(new Date());
        this.update(receiveEntity,queryWrapper);
    }

    /**
     * 根据收货编号获取收货地址
     * @param receiveId 收货编号
     * @return 收货地址信息
     */
    @Override
    public UserReceiveVo getReceiveById(String receiveId) {
        UserReceiveVo userReceiveVo = new UserReceiveVo();
        QueryWrapper<ReceiveEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_no",receiveId);
        ReceiveEntity receiveEntity = this.getOne(queryWrapper);
        BeanUtils.copyProperties(receiveEntity,userReceiveVo);
        return userReceiveVo;
    }

}