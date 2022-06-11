package com.nbrt.kybao.homecare.service.impl;

import com.nbrt.kybao.homecare.dao.CatItemDao;
import com.nbrt.kybao.homecare.dao.CategoryCatitemRelationDao;
import com.nbrt.kybao.homecare.entity.*;
import com.nbrt.kybao.homecare.service.ServerCatitemRelationService;
import com.nbrt.kybao.homecare.service.ServerService;
import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.Query;
import com.nbrt.kybao.homecare.vo.CategoryItemVo;
import com.nbrt.kybao.homecare.vo.CategoryVo;

import com.nbrt.kybao.homecare.vo.server.ChildVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.nbrt.kybao.homecare.dao.CategoryDao;
import com.nbrt.kybao.homecare.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


    @Autowired
    private CategoryCatitemRelationDao categoryCatitemRelationDao;
    @Autowired
    private CatItemDao catItemDao;
    @Autowired
    private ServerCatitemRelationService serverCatitemRelationService;
    @Autowired
    private ServerService serverService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    /**
     * 获取首页数据
     * @return 返回所有分类以及分类下的所有分类项数据
     */
    @Override
    public List<CategoryVo> getWelcomeData() {
        //查询所有分类
        List<CategoryEntity> categoryEntities = this.baseMapper.selectList(null);
        //查询所有分类下的所有分类项
        List<CategoryVo> categoryVos = categoryEntities.stream().map(
                item -> {
                    CategoryVo categoryVo = new CategoryVo();
                    //设置分类名
                    categoryVo.setCategoryName(item.getCatergoryName());
                    //设置分类项
                    QueryWrapper<CategoryCatitemRelationEntity> wrapper = new QueryWrapper<>();
                    wrapper.eq("category_id", item.getCatId());
                    List<CategoryCatitemRelationEntity> categoryCatitemRelationEntities = categoryCatitemRelationDao.selectList(wrapper);
                    List<Long> catItemIds = categoryCatitemRelationEntities.stream().map(categoryCatitemRelationEntity ->
                            categoryCatitemRelationEntity.getCatItemId()).collect(Collectors.toList());
                    List<CatItemEntity> catItemEntities = catItemDao.selectBatchIds(catItemIds);
                    List<CategoryItemVo> collect = catItemEntities.stream().map(catItem -> {
                        CategoryItemVo categoryItemVo = new CategoryItemVo();
                        categoryItemVo.setCatItemId(catItem.getCatItemId());
                        categoryItemVo.setCatItemName(catItem.getCatItemName());
                        categoryItemVo.setIcon(catItem.getIcon());
                        return categoryItemVo;
                    }).collect(Collectors.toList());
                    categoryVo.setCategoryItemList(collect);
                    return categoryVo;
                }
        ).collect(Collectors.toList());
        return categoryVos;
    }

    /**
     *  查出所有一级分类
     */
    public List<ChildVo> getLevel1Categorys() {
        List<CategoryEntity> categoryEntities = this.list(null);
        List<ChildVo> collect1 = categoryEntities.stream().map(item -> {
            ChildVo childVo = new ChildVo();
            childVo.setValue(item.getCatId());
            childVo.setLabel(item.getCatergoryName());
            return childVo;
        }).collect(Collectors.toList());


        List<ChildVo> res = collect1.stream().map(item -> {
            QueryWrapper<CategoryCatitemRelationEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_id", item.getValue());
            List<CategoryCatitemRelationEntity> categoryCatitemRelationEntities = categoryCatitemRelationDao.selectList(queryWrapper);
            List<Long> ids = categoryCatitemRelationEntities.stream().map(id -> {
                Long catItemId = id.getCatItemId();
                return catItemId;
            }).collect(Collectors.toList());


            List<CatItemEntity> catItemEntities = catItemDao.selectBatchIds(ids);

            List<ChildVo> collect2 = catItemEntities.stream().map(catitem -> {
                ChildVo childVo2 = new ChildVo();
                childVo2.setValue(catitem.getCatItemId());
                childVo2.setLabel(catitem.getCatItemName());
                List<ChildVo> childVos = getServer(catitem);
                childVo2.setChildren(childVos);
                return childVo2;
            }).collect(Collectors.toList());
            item.setChildren(collect2);
            return item;
//
//            for (ChildVo childVo : collect1) {
//                childVo.setChildVos();
//            }
//
//            collect2.stream().map(server -> {
//
//            }).collect(Collectors.toList());
////            QueryWrapper<CategoryCatitemRelationEntity> wrapper = new QueryWrapper<>();
////            wrapper.eq("cat_item_id",catitem.getId());
////            List<CategoryCatitemRelationEntity> categoryCatitemRelationEntities = categoryCatitemRelationDao.selectList(queryWrapper);
//
//            item.setChildVos();
        }).collect(Collectors.toList());
        return res;
    }

    /**
     * 获取服务项
     * @param catitem 分类项
     * @return
     */
    private List<ChildVo> getServer(CatItemEntity catitem) {
        Long catItemId = catitem.getCatItemId();
        QueryWrapper<ServerCatitemRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cat_item_id",catItemId);
        List<ServerCatitemRelationEntity> serverCatitemRelationEntities = serverCatitemRelationService.list(queryWrapper);
        List<Long> ids = serverCatitemRelationEntities.stream().map(id -> {
            return id.getServerId();
        }).collect(Collectors.toList());
        if(ids != null && ids.size() > 0) {
            List<ServerEntity> serverEntities = serverService.listByIds(ids);
            List<ChildVo> collect = serverEntities.stream().map(item -> {
                ChildVo childVo = new ChildVo<>();
                childVo.setValue(item.getServerId());
                childVo.setLabel(item.getServerName());
                childVo.setChildren(null);
                return childVo;
            }).collect(Collectors.toList());
            return collect;
        }
        return null;
    }

//    /**
//     *  查出所有二级/三级分类
//     */
//    public Map<String, List<CategoryJsonVo>> getCategoryJson() {
//
////        System.out.println("查询了数据库");
//        //将多次查询数据库变成一次查询
//        List<CategoryEntity> categoryEntities = this.baseMapper.selectList(null);
//
//        //查询所有一级分类
//        List<CategoryEntity> level1Categorys = this.getCategoryEntities(categoryEntities,0L);
//
//        //封装数据
//        Map<String, List<CategoryJsonVo>> resultMap = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
//            List<CategoryEntity> categoryLevel2 = getCategoryEntities(categoryEntities,v.getCatId());
//            List<CategoryJsonVo> category2Vos = null;
//            if (categoryLevel2 != null) {
//                category2Vos = categoryLevel2.stream().map(l2 -> {
//                    CategoryJsonVo category2Vo = new CategoryJsonVo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
//                    List<CategoryEntity> categoryLevel3 = getCategoryEntities(categoryEntities,l2.getCatId());
//                    if (categoryLevel3 != null) {
//                        List<CategoryJsonVo.Category3Vo> category3Vos = categoryLevel3.stream().map(l3 -> {
//                            CategoryJsonVo.Category3Vo category3Vo = new CategoryJsonVo.Category3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
//                            return category3Vo;
//                        }).collect(Collectors.toList());
//                        category2Vo.setCatalog3List(category3Vos);
//                    }
//                    return category2Vo;
//                }).collect(Collectors.toList());
//            }
//            return category2Vos;
//        }));
//        return resultMap;
//    }
//}
}