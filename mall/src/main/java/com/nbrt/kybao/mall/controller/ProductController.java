package com.nbrt.kybao.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nbrt.kybao.mall.dto.common.ApiResult;
import com.nbrt.kybao.mall.enums.CodeEnum;
import com.nbrt.kybao.mall.model.dto.AddProductDto;
import com.nbrt.kybao.mall.model.dto.ProductDto;
import com.nbrt.kybao.mall.model.entity.Category;
import com.nbrt.kybao.mall.model.entity.Img;
import com.nbrt.kybao.mall.model.entity.Product;
import com.nbrt.kybao.mall.model.entity.Property;
import com.nbrt.kybao.mall.model.vo.AppProductDetailVo;
import com.nbrt.kybao.mall.model.vo.AppProductListVo;
import com.nbrt.kybao.mall.model.vo.ProductInfoVo;
import com.nbrt.kybao.mall.service.api.CategoryService;
import com.nbrt.kybao.mall.service.api.ImgService;
import com.nbrt.kybao.mall.service.api.ProductService;
import com.nbrt.kybao.mall.service.api.PropertyService;
import com.nbrt.kybao.mall.util.OssUtil;
import com.nbrt.kybao.mall.util.RandomStringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@CrossOrigin
@RestController
@RequestMapping("/product")
@Api(tags = "商品控制")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImgService imgService;

    @Autowired
    private PropertyService propertyService;

    @ApiOperation("管理端-添加商品")
    @PostMapping("/one")
    public ApiResult<String> addOneProduct(
            @RequestBody AddProductDto addProductDto
    ) {
        System.out.println(addProductDto);
        Product product=new Product();
        String randomProNo = RandomStringUtil.getRandomString(8);
        System.out.println(randomProNo);
        BeanUtils.copyProperties(addProductDto,product);
        product.setProNo(randomProNo);
        System.out.println(product);
        // 给商品添加分类编号
        // 获取商品分类编号
        Category one = categoryService.getOne(new LambdaQueryWrapper<Category>().eq(Category::getTitle, addProductDto.getCateName()));
        product.setCateId(one.getId());
        // 商品轮播图添加进数据库
        int count=0;
        // 新建图片对象
        for(String head:addProductDto.getImgListHead()){
            Img img=new Img();
            img.setImgNo(RandomStringUtil.getRandomString(8));
            img.setProNo(product.getProNo());
            img.setImgPath(head);
            img.setImgType(1);
            imgService.save(img);
            count++;
        }
        if (count!=addProductDto.getImgListHead().size()){
            return ApiResult.failedWithMessage(CodeEnum.UPLOAD_ERROR.getCode(), CodeEnum.UPLOAD_ERROR.getMessage());
        }
        count=0;
        for(String bottom:addProductDto.getImgListBottom()){
            Img img=new Img();
            img.setImgNo(RandomStringUtil.getRandomString(8));
            img.setProNo(product.getProNo());
            img.setImgPath(bottom);
            img.setImgType(2);
            imgService.save(img);
            count++;
        }
        if (count!=addProductDto.getImgListBottom().size()){
            return ApiResult.failedWithMessage(CodeEnum.UPLOAD_ERROR.getCode(), CodeEnum.UPLOAD_ERROR.getMessage());
        }
        // 添加属性
        int stockCount=0;
        for (Property property : addProductDto.getPropertyList()) {
            property.setPropertyNo(RandomStringUtil.getRandomString(8));
            property.setProNo(randomProNo);
            stockCount+=product.getStock();
            propertyService.save(property);
        }
        // 为商品添加总库存
        product.setStock(stockCount);
        productService.save(product);
        return ApiResult.successWithMessage("添加成功");
    }

    @ApiOperation("管理端-修改商品")
    @PutMapping("/update")
    public ApiResult<String> updateOneProduct(
            @RequestBody AddProductDto addProductDto
    ) {
        // 首先根据商品编号获取商品信息
        Product product=productService.getOne(new LambdaQueryWrapper<Product>().eq(Product::getProNo,addProductDto.getProNo()));
        // 将传递的商品参数进行更新
        BeanUtils.copyProperties(addProductDto,product);
        // 更新商品类别
        Category one = categoryService.getOne(new LambdaQueryWrapper<Category>().eq(Category::getTitle, addProductDto.getCateName()));
        product.setCateId(one.getId());
        // 更新商品相关图片
        int count=0;
        // 删除商品相关图片信息
        imgService.remove(new LambdaQueryWrapper<Img>().eq(Img::getProNo,product.getProNo()));
        // 新建图片对象
        for(String head:addProductDto.getImgListHead()){
            Img img=new Img();
            img.setImgNo(RandomStringUtil.getRandomString(8));
            img.setProNo(product.getProNo());
            img.setImgPath(head);
            img.setImgType(1);
            imgService.save(img);
            count++;
        }
        if (count!=addProductDto.getImgListHead().size()){
            return ApiResult.failedWithMessage(CodeEnum.UPLOAD_ERROR.getCode(), CodeEnum.UPLOAD_ERROR.getMessage());
        }
        count=0;
        for(String bottom:addProductDto.getImgListBottom()){
            Img img=new Img();
            img.setImgNo(RandomStringUtil.getRandomString(8));
            img.setProNo(product.getProNo());
            img.setImgPath(bottom);
            img.setImgType(2);
            imgService.save(img);
            count++;
        }
        if (count!=addProductDto.getImgListBottom().size()){
            return ApiResult.failedWithMessage(CodeEnum.UPLOAD_ERROR.getCode(), CodeEnum.UPLOAD_ERROR.getMessage());
        }
        // 更新属性
        int stockCount=0;
        for (Property property : addProductDto.getPropertyList()) {
            property.setPropertyNo(RandomStringUtil.getRandomString(8));
            property.setProNo(property.getProNo());
            stockCount+=product.getStock();
            propertyService.save(property);
        }
        // 更新总库存
        product.setStock(stockCount);
        productService.save(product);
        return ApiResult.successWithMessage("修改成功");
    }

    @ApiOperation("管理端-获取商品信息")
    @GetMapping("/getOne/{proNo}")
    public ApiResult<AddProductDto> updateOneProduct(
            @PathVariable("proNo") String proNo
    ) {
        // 新建商品添加视图对象
        AddProductDto addProductDto=new AddProductDto();
        // 首先根据商品的编号获取商品的信息
        Product product=productService.getOne(new LambdaQueryWrapper<Product>().eq(Product::getProNo,proNo));
        // 将商品信息复制到视图独享中
        BeanUtils.copyProperties(product,addProductDto);
        // 为视图对象添加属性
        // 根据编号查询该商品下所有的属性
        List<Property> list = propertyService.list(new LambdaQueryWrapper<Property>().eq(Property::getProNo, proNo));
        addProductDto.setPropertyList(list);
        // 查询所有商品轮播图
        List<String> imgListHead = imgService.listObjs(
                new LambdaQueryWrapper<Img>()
                        .select(Img::getImgPath)
                        .eq(Img::getProNo, proNo)
                        .eq(Img::getImgType,1)
                )
                .stream().map(o -> (String) o).collect(Collectors.toList());
        addProductDto.setImgListHead(imgListHead);
        // 查询所有商品详情图
        List<String> imgListBottom = imgService.listObjs(
                        new LambdaQueryWrapper<Img>()
                                .select(Img::getImgPath)
                                .eq(Img::getProNo, proNo)
                                .eq(Img::getImgType,2)
                )
                .stream().map(o -> (String) o).collect(Collectors.toList());
        addProductDto.setImgListBottom(imgListBottom);
        return ApiResult.successWithMessageAndData(CodeEnum.SUCCESS.getMessage(), addProductDto);
    }

    @ApiOperation("管理端-删除单个商品")
    @DeleteMapping("/deleteOneById/{proNo}")
    public ApiResult<String> deleteOneById(@PathVariable("proNo") String proNo) {
        System.out.println("aaa");
        int result = productService.removeOneByNo(proNo);
        if (result != 0) {
            return ApiResult.successWithMessage("删除成功！");
        }
        return ApiResult.failedWithMessage(CodeEnum.DELETE_ERROR.getCode(), CodeEnum.DELETE_ERROR.getMessage());
    }

    @ApiOperation("管理端-批量删除商品")
    @DeleteMapping("/deleteByIds")
    public ApiResult<String> deleteOneProduct(@RequestParam("ids") String[] proNo) {
        if (productService.removeOneByNos(proNo)==proNo.length){
            return ApiResult.successWithMessage("删除成功！");
        }
        return ApiResult.failedWithMessage(CodeEnum.DELETE_ERROR.getCode(), CodeEnum.DELETE_ERROR.getMessage());
    }


    @ApiOperation("管理端-更新单个商品")
    @PutMapping("/one")
    public ApiResult<String> deleteOneProduct(@RequestBody Product product) {
        return null;
    }

    @ApiOperation("管理端-获取(某一类)商品列表 必须分页参数：当前页，页大小")
    @PostMapping(value = "/getListInfo")
    public ApiResult<IPage<ProductInfoVo>> getProducts(
            @RequestBody ProductDto productDto
    ) {
        IPage<ProductInfoVo> list=productService.getListInfo(productDto);
        return ApiResult.successWithMessageAndData(CodeEnum.SUCCESS.getMessage(),list);
    }

    @ApiOperation("管理端-是否推荐商品")
    @PutMapping("/recommend")
    public ApiResult<String> recommendOneProduct(@RequestBody ProductInfoVo productInfoVo){
        return productService.recommendOne(productInfoVo);
    }

    @ApiOperation("管理端-上下架商品")
    @PutMapping("/shelf")
    public ApiResult<String> shelfOneProduct(@RequestBody ProductInfoVo productInfoVo){
        return productService.shelfOne(productInfoVo);
    }

    @ApiOperation("移动端-获取推荐商品信息")
    @GetMapping("/recommend")
    public ApiResult<List<Product>> getRecommend(){
        // 查看缓存中是否有商品列表，如果有则直接返回

        List<Product> list = productService.list(new LambdaQueryWrapper<Product>().eq(Product::getRecommend, 1));
        return ApiResult.successWithMessageAndData(CodeEnum.SUCCESS.getMessage(),list);
    }

    @ApiOperation("移动端-获取某一类商品列表")
    @GetMapping(value = "/getAppListInfo")
    public ApiResult<List<AppProductListVo>> getProducts(
            @RequestParam("cateId") Integer cateId
    ) {
        List<AppProductListVo> list=productService.getAppListInfo(cateId);
        return ApiResult.successWithMessageAndData(CodeEnum.SUCCESS.getMessage(),list);
    }

    @ApiOperation("移动端-获取商品详情")
    @GetMapping(value = "/getAppProductDetail/{proNo}")
    public ApiResult<AppProductDetailVo> getProductDetail(
            @PathVariable("proNo") String proNo
    ) {
        AppProductDetailVo detailVo=productService.getAppProductDetailByNo(proNo);
        return ApiResult.successWithMessageAndData(CodeEnum.SUCCESS.getMessage(),detailVo);
    }
}


