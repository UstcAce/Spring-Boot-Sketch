package com.example.demo.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    public void contextLoads() {
        String key = "name";
        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key, "BlinkAce");
        System.out.println(redisTemplate.opsForValue().get(key));

        redisTemplate.opsForValue().set(key, "汪敏");
    }
}
