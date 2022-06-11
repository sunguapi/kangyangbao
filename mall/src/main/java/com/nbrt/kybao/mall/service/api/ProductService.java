package com.nbrt.kybao.mall.service.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nbrt.kybao.mall.dto.common.ApiResult;
import com.nbrt.kybao.mall.model.dto.ProductDto;
import com.nbrt.kybao.mall.model.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nbrt.kybao.mall.model.vo.AppProductDetailVo;
import com.nbrt.kybao.mall.model.vo.AppProductListVo;
import com.nbrt.kybao.mall.model.vo.OrderPageInfoVo;
import com.nbrt.kybao.mall.model.vo.ProductInfoVo;

import java.util.List;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author hjh
 * @since 2022 -05-05
 */
public interface ProductService extends IService<Product> {

    /**
     * 商品单个删除
     *
     * @param proNo the pro no
     * @return the int
     */
    int removeOneByNo(String proNo);

    /**
     * 商品批量删除
     *
     * @param proNo the pro no
     * @return the int
     */
    int removeOneByNos(String[] proNo);

    /**
     * 获取商品列表信息（模糊查询分页）
     *
     * @param productDto the order page info dto
     * @return the list info
     */
    IPage<ProductInfoVo> getListInfo(ProductDto productDto);

    /**
     * 是否推荐商品
     *
     * @param productInfoVo the proInfo
     * @return the api result
     */
    ApiResult<String> recommendOne(ProductInfoVo productInfoVo);

    /**
     * 是否下架商品
     *
     * @param productInfoVo the proInfo
     * @return the api result
     */
    ApiResult<String> shelfOne(ProductInfoVo productInfoVo);

    /**
     * 根据商品类型获取商品列表
     *
     * @param cateId the cate id
     * @return the app list info
     */
    List<AppProductListVo> getAppListInfo(Integer cateId);

    /**
     * Gets app product detail by no.
     *
     * @param proNo the pro no
     * @return the app product detail by no
     */
    AppProductDetailVo getAppProductDetailByNo(String proNo);
}
