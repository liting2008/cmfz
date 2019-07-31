package com.baizhi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test1(){
        stringRedisTemplate.opsForValue().set("name","lisi");
    }
    @Test
    public void test2(){
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
        System.out.println("第1次提交5646541");
        System.out.println("第2次提交");
        System.out.println("第3次提交");
        System.out.println("第4次提交");
        System.out.println("==========");
        System.out.println("++++++++++");
        System.out.println("----------");
    }
}
