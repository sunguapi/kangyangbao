package com.nbrt.kybao.mall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class MallApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("key1","测试");

    }

    @Test
    void testRedisGet() {
        Object key1 = redisTemplate.opsForValue().get("key1");
        System.out.println(key1);

    }

}
