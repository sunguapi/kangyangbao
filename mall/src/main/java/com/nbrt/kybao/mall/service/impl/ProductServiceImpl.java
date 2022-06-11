package com.nbrt.kybao.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbrt.kybao.mall.mapper.CategoryMapper;
import com.nbrt.kybao.mall.mapper.ImgMapper;
import com.nbrt.kybao.mall.mapper.PropertyMapper;
import com.nbrt.kybao.mall.model.constant.KybConstant;
import com.nbrt.kybao.mall.dto.common.ApiResult;
import com.nbrt.kybao.mall.model.dto.ProductDto;
import com.nbrt.kybao.mall.model.entity.Category;
import com.nbrt.kybao.mall.model.entity.Img;
import com.nbrt.kybao.mall.model.entity.Product;
import com.nbrt.kybao.mall.enums.CodeEnum;
import com.nbrt.kybao.mall.mapper.ProductMapper;
import com.nbrt.kybao.mall.model.entity.Property;
import com.nbrt.kybao.mall.model.vo.AppProductDetailVo;
import com.nbrt.kybao.mall.model.vo.AppProductListVo;
import com.nbrt.kybao.mall.service.api.ProductService;
import com.nbrt.kybao.mall.model.vo.OrderPageInfoVo;
import com.nbrt.kybao.mall.model.vo.ProductInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Slf4j
@Service
@Transactional
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private ImgMapper imgMapper;

    @Override
    public int removeOneByNo(String proNo) {
        // 1 判断商品编号为传递参数的商品是否存在
        // 2 逻辑删除
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Product::getProNo,proNo);
        Product one = productMapper.selectOne(queryWrapper);
        System.out.println(one);
        if (one==null){
            return 0;
        }
        return productMapper.delete(queryWrapper);
    }

    @Override
    public int removeOneByNos(String[] proNo) {
        int count=0;
        for (int i = 0; i < proNo.length; i++) {
            count+= removeOneByNo(proNo[i]);
        }
        return count;
    }

    @Override
    public IPage<ProductInfoVo> getListInfo(ProductDto productDto) {
        QueryWrapper<ProductInfoVo> wrapper=new QueryWrapper<>();
        wrapper.lambda().and(StringUtils.isNotBlank(productDto.getSearchString()),
                wrappers->wrappers.like(ProductInfoVo::getProName,productDto.getSearchString()));
        // 新建分页
        Page<ProductInfoVo> page = new Page<>(
                Long.parseLong(productDto.getCurrentPage()),
                Long.parseLong(productDto.getPageSize()));
        System.out.println(page);
        IPage<ProductInfoVo> iPage = productMapper.queryPageInfo(
                page,
                productDto.getCategoryName(),
                productDto.getStartTime(),
                productDto.getEndTime(),
                productDto.getSearchString()
        );
        System.out.println(iPage.getRecords().size());
        return iPage;
    }

    @Override
    public ApiResult<String> recommendOne(ProductInfoVo productInfoVo) {
        /*
         * 1 判断该商品是否存在
         * 2 判断推荐商品与否
         * 3 推荐商品：查询已推荐商品数量是否已满，推荐商品数量最大为三，已满返回消息，未满则推荐操作
         *   取消推荐商品：取消推荐操作
         */
        Product product = productMapper.selectOne(new LambdaQueryWrapper<Product>().eq(Product::getProNo, productInfoVo.getProNo()));
        if (product==null){
            return ApiResult.failedWithMessage(CodeEnum.UN_EXIST.getCode(),CodeEnum.UN_EXIST.getMessage());
        }
        if (!product.getRecommend().equals(productInfoVo.getRecommend())){
            product.setRecommend(productInfoVo.getRecommend());
            productMapper.updateById(product);
            return ApiResult.success();
        }else{
            return ApiResult.failedWithMessage(CodeEnum.REPEAT_OPERATE.getCode(),CodeEnum.REPEAT_OPERATE.getMessage());
        }
    }

    @Override
    public ApiResult<String> shelfOne(ProductInfoVo productInfoVo) {
        /*
         * 1 判断该商品是否存在
         * 2 判断商品下架与否
         * 3 上架或下架商品：执行操作
         */
        Product product = productMapper.selectOne(new LambdaQueryWrapper<Product>().eq(Product::getProNo, productInfoVo.getProNo()));
        if (product==null){
            return ApiResult.failedWithMessage(CodeEnum.UN_EXIST.getCode(),CodeEnum.UN_EXIST.getMessage());
        }
        if (!product.getUpDown().equals(productInfoVo.getUpDown())){
            product.setUpDown(productInfoVo.getUpDown());
            productMapper.updateById(product);
            return ApiResult.success();
        }else{
            return ApiResult.failedWithMessage(CodeEnum.REPEAT_OPERATE.getCode(),CodeEnum.REPEAT_OPERATE.getMessage());
        }
    }

    @Override
    public List<AppProductListVo> getAppListInfo(Integer cateId) {
        return productMapper.getProductListByCateId(cateId);
    }

    @Override
    public AppProductDetailVo getAppProductDetailByNo(String proNo) {
        AppProductDetailVo appProductDetailVo=new AppProductDetailVo();
        // 获取商品详情
        Product product = productMapper.selectOne(new LambdaQueryWrapper<Product>().eq(Product::getProNo, proNo));
        BeanUtils.copyProperties(product,appProductDetailVo);
        // 根据商品类型获取类型编号
        Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>().eq(Category::getId, product.getCateId()));
        appProductDetailVo.setCateName(category.getTitle());
        // 添加所有规格的货物信息
        List<Property> properties = propertyMapper.selectList(new LambdaQueryWrapper<Property>().eq(Property::getProNo, proNo));
        appProductDetailVo.setPropertyList(properties);
        // 注入商品详情的轮播图
        List<Img> imgs1 = imgMapper.selectList(new LambdaQueryWrapper<Img>().eq(Img::getImgType, 1).eq(Img::getProNo, proNo));
        appProductDetailVo.setImgListHead(imgs1);
        // 注入商品详情的图片列表
        List<Img> imgs2 = imgMapper.selectList(new LambdaQueryWrapper<Img>().eq(Img::getImgType, 2).eq(Img::getProNo, proNo));
        appProductDetailVo.setImgListBottom(imgs2);
        return appProductDetailVo;
    }
}