package com.nbrt.kybao.mall.controller;


import com.nbrt.kybao.mall.dto.common.ApiResult;
import com.nbrt.kybao.mall.model.entity.Product;
import com.nbrt.kybao.mall.service.api.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@CrossOrigin
@RestController
@RequestMapping("/cart")
@Api(tags = "购物车")
public class CartController {

    @Autowired
    private CartService cartService;

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public ApiResult<String> addCommodity(@RequestBody Product product){

        return null;
    }

}

