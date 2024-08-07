package com.sprata.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void stringOpstest() {
        ValueOperations<String, String> ops
                = stringRedisTemplate.opsForValue();
        ops.set("simplekey", "simplevalue");
        System.out.println(ops.get("simplekey"));
    }

    @Test
    public void stringSetOpsTest() {
        // 집합을 조작하기 위한 클래스
        SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
        setOps.add("hobbies", "games");
        setOps.add("hobbies", "coding");
        setOps.add("hobbies", "alcohol");
        setOps.add("hobbies", "games");
        System.out.println(setOps.size("hobbies"));

        stringRedisTemplate.expire("hobbies",10, TimeUnit.SECONDS);
        stringRedisTemplate.delete("simplekey");
    }

    @Autowired
    private RedisTemplate<String, ItemDto> itemredisTemplate;

    @Test
    public void setItemredisTemplatetest() {
        ValueOperations<String, ItemDto> ops
                = itemredisTemplate.opsForValue();
        ops.set("my:keyboard", ItemDto.builder()
                .name("Mechanical Keyboard")
                .price(300000)
                .description("Expensive 😢")
                .build());
        System.out.println(ops.get("my:keyboard"));

        ops.set("my:mouse", ItemDto.builder()
                .name("mouse mice")
                .price(100000)
                .description("Expensive 😢")
                .build());
        System.out.println(ops.get("my:mouse"));
    }

}
