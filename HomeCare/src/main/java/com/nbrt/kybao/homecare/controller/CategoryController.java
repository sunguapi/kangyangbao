package com.nbrt.kybao.homecare.controller;


import java.util.List;

import com.nbrt.kybao.homecare.utils.R;
import com.nbrt.kybao.homecare.vo.CategoryVo;

import com.nbrt.kybao.homecare.vo.server.ChildVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.homecare.entity.CategoryEntity;
import com.nbrt.kybao.homecare.service.CategoryService;




/**
 * 服务分类
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/category")
@Api(tags = "分类")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    /**
//     * 列表
//     */
//    @ApiOperation("查询分类列表")
//    @GetMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = categoryService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }



    @ApiOperation("(移动端)获取三级分类数据")
    @GetMapping("/getLevel1Categorys")
    public R getLevel1Categorys() {
        List<ChildVo> level1Categorys = categoryService.getLevel1Categorys();
        return R.ok().put("option",level1Categorys);
    }

    /**
     * 信息
     */
    @ApiOperation("(移动端)查询分类信息")
    @GetMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }


    @ApiOperation("(移动端)获取首页数据")
    @GetMapping("/welcomeData")
    public R getWelcomeData() {
        List<CategoryVo> categoryList = categoryService.getWelcomeData();
        return R.ok().put("data",categoryList);
    }

//    /**
//     * 保存
//     */
//    @ApiOperation("保存分类")
//    @PostMapping("/save")
//    public R save(@RequestBody CategoryEntity category){
//		categoryService.save(category);
//
//        return R.ok();
//    }

//    /**
//     * 修改
//     */
//    @ApiOperation("修改分类")
//    @PostMapping("/update")
//    public R update(@RequestBody CategoryEntity category){
//		categoryService.updateById(category);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @ApiOperation("删除分类")
//    @DeleteMapping("/delete")
//    public R delete(@RequestBody Long[] catIds){
//		categoryService.removeByIds(Arrays.asList(catIds));
//
//        return R.ok();
//    }

}
