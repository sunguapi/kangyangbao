package com.nbrt.kybao.mall.service.test;

import com.nbrt.kybao.mall.model.entity.Product;
import com.nbrt.kybao.mall.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * @author hjh
 * @date 2022/5/5 14:32
 */
@Slf4j
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void saveProduct(){
        ArrayList<Product> arrayList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product=new Product();
            product.setCateId(1);
            product.setDeleted(0);
            product.setId(i);;
            product.setProDesc("治疗维生素与矿物质缺乏");
            product.setProNo(String.valueOf(UUID.randomUUID()));
            product.setKeywords("维生素");
            product.setProName("斯威特老人复合维生素");
            product.setPrice(BigDecimal.valueOf(999.99));
            product.setMainImg("空");
            product.setPv(200);
            product.setCreateTime(new Date());
            product.setBrand("Swiss");
            product.setSubTitle("维生素C");
            product.setSold(40);
            product.setStock(500);
            arrayList.add(product);
        }
        arrayList.forEach(System.out::println);
        productService.saveBatch(arrayList);
    }

}