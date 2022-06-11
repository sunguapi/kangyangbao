package com.nbrt.kybao.mall.controller;


import com.nbrt.kybao.mall.dto.common.ApiResult;
import com.nbrt.kybao.mall.enums.CodeEnum;
import com.nbrt.kybao.mall.model.entity.Category;
import com.nbrt.kybao.mall.service.api.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品分类 前端控制器
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@CrossOrigin
@RestController
@RequestMapping("/category")
@Api(tags = "商品分类")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取所有商品分类")
    @GetMapping("/allCategory")
    public ApiResult<List<Category>> getCateGoryList(){
        List<Category> list = categoryService.list();
        return ApiResult.successWithMessageAndData(CodeEnum.SUCCESS.getMessage(),list);
    }
}

