package com.nbrt.kybao.homecare.controller;

import java.util.Arrays;
import java.util.Map;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.homecare.entity.CategoryCatitemRelationEntity;
import com.nbrt.kybao.homecare.service.CategoryCatitemRelationService;




/**
 * 分类分类项关联
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/categorycatitemrelation")
//@Api(tags = "分类分类项关联")
public class CategoryCatitemRelationController {
//    private final CategoryCatitemRelationService categoryCatitemRelationService;
//
//    public CategoryCatitemRelationController(CategoryCatitemRelationService categoryCatitemRelationService) {
//        this.categoryCatitemRelationService = categoryCatitemRelationService;
//    }
//
//    /**
//     * 列表
//     */
//    @ApiOperation("查询分类分类项关联信息")
//    @GetMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = categoryCatitemRelationService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @ApiOperation("查询分类分类项关联信息")
//    @GetMapping("/info/{id}")
//    public R info(@PathVariable("id") Long id){
//		CategoryCatitemRelationEntity categoryCatitemRelation = categoryCatitemRelationService.getById(id);
//
//        return R.ok().put("categoryCatitemRelation", categoryCatitemRelation);
//    }
//
//    /**
//     * 保存
//     */
//    @ApiOperation("保存分类分类项关联信息")
//    @PostMapping("/save")
//    public R save(@RequestBody CategoryCatitemRelationEntity categoryCatitemRelation){
//		categoryCatitemRelationService.save(categoryCatitemRelation);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @ApiOperation("修改分类分类项关联信息")
//    @PostMapping("/update")
//    public R update(@RequestBody CategoryCatitemRelationEntity categoryCatitemRelation){
//		categoryCatitemRelationService.updateById(categoryCatitemRelation);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @ApiOperation("删除分类分类项关联信息")
//    @DeleteMapping("/delete")
//    public R delete(@RequestBody Long[] ids){
//		categoryCatitemRelationService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

}
