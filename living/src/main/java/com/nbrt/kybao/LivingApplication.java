package com.nbrt.kybao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nbrt.kybao.mapper")
public class LivingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivingApplication.class, args);
    }

}
