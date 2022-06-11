package com.nbrt.kybao.mall.generator;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 代码生成器
 * </p>
 *
 * @author hjh
 * @date 2022/5/5 11:12
 */
public class CodeGenerator {

    public static void main(String[] args) {
        //表名集合
        List<String> tables = new ArrayList<>();
        tables.add("tab_cart");
        tables.add("tab_category");
        tables.add("tab_contents");
        tables.add("tab_img");
        tables.add("tab_item");
        tables.add("tab_orders");
        tables.add("tab_payinfo");
        tables.add("tab_product");
        tables.add("tab_proname");
        tables.add("tab_provalue");
        tables.add("tab_property");
        tables.add("tab_shopping");
        tables.add("tab_stock");

        FastAutoGenerator.create("jdbc:mysql://192.168.3.89:3306/kyb?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC",
                        "root",
                        "root123")
                .globalConfig(builder -> {
                    builder.author("hjh")//作者
                            .outputDir("E:\\idea_workspace\\Project\\kybao\\mall\\src\\main\\java")//输出路径(写到java目录)
                            .enableSwagger()//开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();//开启覆盖之前生成的文件

                })
                .packageConfig(builder -> {
                    builder.parent("com.nbrt.kybao.mall")//指定父包名
                            .entity("entity")//指定实体包名
                            .service("service.api")//指定service接口包名
                            .serviceImpl("service.impl")//service实现类包名
                            .controller("controller")//指定控制器包名
                            .mapper("mapper")//指定mapper接口包名
                            .xml("mapper")//xml包名
                            .pathInfo(
                                    Collections.singletonMap(
                                            OutputFile.mapperXml,
                                            System.getProperty("user.dir") +
                                                    "\\mall\\src\\main\\resources\\com\\nbrt\\kybao\\mall\\mapper")
                            );//指定xml位置
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .addTablePrefix("tab_")//表名前缀，配置后生成的代码不会有此前缀
                            .serviceBuilder()
                            .formatServiceFileName("%sService")//服务层接口名后缀
                            .formatServiceImplFileName("%sServiceImpl")//服务层实现类名后缀
                            .entityBuilder()
                            .enableLombok()//实体类使用lombok,需要自己引入依赖
                            .logicDeleteColumnName("status")//逻辑删除字段，使用delete方法删除数据时会将status设置为1。调用update方法时并不会将该字段放入修改字段中，而是在条件字段中
                            .enableTableFieldAnnotation()//加上字段注解@TableField
                            .controllerBuilder()
                            .formatFileName("%sController")//控制类名称后缀
                            .enableRestStyle()
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .formatXmlFileName("%sMapper");
                })
                .execute();
    }

}
