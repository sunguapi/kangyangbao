package com.nbrt.kybao.mall.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbrt.kybao.mall.model.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbrt.kybao.mall.model.vo.AppProductListVo;
import com.nbrt.kybao.mall.model.vo.ProductInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author hjh
 * @since 2022-05-05
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    IPage<ProductInfoVo> queryPageInfo(
            @Param("page") Page<ProductInfoVo> page,
            @Param("categoryName") String categoryName,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("like") String searchString
    );

    List<AppProductListVo> getProductListByCateId(Integer cateId);
}
