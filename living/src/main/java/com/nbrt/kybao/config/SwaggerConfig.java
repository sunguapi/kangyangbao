package com.nbrt.kybao.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger配置
 *
 * @since 1.0.0
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket web_api_college() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("康养宝")
                .apiInfo(new ApiInfoBuilder()
                        .title("康养宝接口文档")
                        .description("康养宝接口文档")
                        .contact(new Contact("合作高校", "http://localhost:8808/doc.html", "xxxx@xxxx.com"))
                        .version("1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nbrt.kybao.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
