package com.nbrt.kybao.homecare.controller;

import java.util.Arrays;
import java.util.Map;

import com.nbrt.kybao.homecare.utils.PageUtils;
import com.nbrt.kybao.homecare.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.nbrt.kybao.homecare.entity.CatItemEntity;
import com.nbrt.kybao.homecare.service.CatItemService;




/**
 * 服务分类项
 *
 * @author lwc
 * @email 1442374544@qq.com
 * @date 2022-05-23 15:11:38
 */
@CrossOrigin
@RestController
@RequestMapping("homecare/catitem")
@Api(tags = "分类项")
public class CatItemController {
    private final CatItemService catItemService;

    public CatItemController(CatItemService catItemService) {
        this.catItemService = catItemService;
    }

//    /**
//     * 列表
//     */
//    @ApiOperation("获取分类项列表")
//    @GetMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = catItemService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    @ApiOperation("(移动端)获取分类项信息")
    @GetMapping("/info/{catItemId}")
    public R info(@PathVariable("catItemId") Long catItemId){
		CatItemEntity catItem = catItemService.getById(catItemId);

        return R.ok().put("catItem", catItem);
    }

//    /**
//     * 保存
//     */
//    @ApiOperation("保存分类项信息")
//    @PostMapping("/save")
//    public R save(@RequestBody CatItemEntity catItem){
//		catItemService.save(catItem);
//        return R.ok();
//    }

//    /**
//     * 修改
//     */
//    @ApiOperation("修改分类项信息")
//    @PostMapping("/update")
//    public R update(@RequestBody CatItemEntity catItem){
//		catItemService.updateById(catItem);
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @ApiOperation("删除分类项信息")
//    @DeleteMapping("/delete")
//    public R delete(@RequestBody Long[] catItemIds){
//		catItemService.removeByIds(Arrays.asList(catItemIds));
//        return R.ok();
//    }

}
