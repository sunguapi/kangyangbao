package com.nbrt.kybao.user.service.impl;

import com.nbrt.kybao.user.entity.AdminInfoEntity;
import com.nbrt.kybao.user.entity.AdminMetaEntity;
import com.nbrt.kybao.user.entity.AdminRouerRelationEntity;
import com.nbrt.kybao.user.service.AdminInfoService;
import com.nbrt.kybao.user.service.AdminMetaService;
import com.nbrt.kybao.user.service.AdminRouerRelationService;
import com.nbrt.kybao.user.utils.JwtHelper;
import com.nbrt.kybao.user.utils.PageUtils;
import com.nbrt.kybao.user.utils.Query;
import com.nbrt.kybao.user.vo.AdminMenuAndInfoVo;
import com.nbrt.kybao.user.vo.AdminRouterVo;
import com.nbrt.kybao.user.dao.AdminRouterDao;
import com.nbrt.kybao.user.entity.AdminRouterEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.user.service.AdminRouterService;
import org.springframework.util.StringUtils;


@Service("adminRouterService")
public class AdminRouterServiceImpl extends ServiceImpl<AdminRouterDao, AdminRouterEntity> implements AdminRouterService {

    @Autowired
    private AdminRouerRelationService adminRouerRelationService;

    @Autowired
    private AdminMetaService adminMetaService;

    @Autowired
    private AdminInfoService adminInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminRouterEntity> page = this.page(
                new Query<AdminRouterEntity>().getPage(params),
                new QueryWrapper<AdminRouterEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 获取路由信息
     * @param token token令牌
     */
    @Override
    public AdminMenuAndInfoVo getRouterInfo(String token) {
        AdminMenuAndInfoVo adminMenuAndInfoVo = new AdminMenuAndInfoVo();
        String username = JwtHelper.getUserName(token);
        QueryWrapper<AdminInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        AdminInfoEntity infoEntity = adminInfoService.getOne(queryWrapper);
        if(infoEntity != null) {
            adminMenuAndInfoVo.setAvatar(infoEntity.getAvatar());
            adminMenuAndInfoVo.setUsername(infoEntity.getUsername());
        }
        if(StringUtils.isEmpty(username)) {
            return null;
        }else {
            QueryWrapper<AdminRouerRelationEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("username",username);
            List<AdminRouerRelationEntity> adminRouerRelationEntities =
                    adminRouerRelationService.getBaseMapper().selectList(wrapper);
            List<Integer> routerIds = null;
            List<AdminRouterEntity> adminRouterEntities = null;
            if(adminRouerRelationEntities != null) {
                routerIds = adminRouerRelationEntities.stream().map(item -> {
                    return item.getRouterId();
                }).collect(Collectors.toList());
                adminRouterEntities = this.listByIds(routerIds);
            }
            if(adminRouterEntities != null) {
                List<AdminRouterVo> adminRouterVoList = adminRouterEntities.stream().map(item -> {
                    AdminRouterVo adminRouterVo = new AdminRouterVo();
                    AdminMetaEntity adminMetaEntity = adminMetaService.getById(item.getMetaId());
                    BeanUtils.copyProperties(item, adminRouterVo);
                    adminRouterVo.setMeta(adminMetaEntity);
                    AdminRouterVo adminRouterVoChild = new AdminRouterVo();
                    AdminRouterEntity adminRouterEntity = this.getById(item.getChildrenId());
                    AdminMetaEntity metaEntity = adminMetaService.getById(adminRouterEntity.getMetaId());
                    BeanUtils.copyProperties(adminRouterEntity,adminRouterVoChild);
                    adminRouterVoChild.setMeta(metaEntity);
                    ArrayList<AdminRouterVo> adminRouterVos = new ArrayList<>();
                    adminRouterVos.add(adminRouterVoChild);
                    adminRouterVo.setChildren(adminRouterVos);
                    return adminRouterVo;
                }).collect(Collectors.toList());
                adminMenuAndInfoVo.setAdminRouterVoList(adminRouterVoList);
            }
            return adminMenuAndInfoVo;
        }
    }
}