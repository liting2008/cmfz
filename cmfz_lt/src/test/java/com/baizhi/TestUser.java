package com.baizhi;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Gender;
import com.baizhi.entity.MonthAndCount;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {
    @Autowired
    private UserService userService;
    @Test
    public void test1(){
        Map<String, Object> map = userService.queryMonthAndCount("男");
        for (String s : map.keySet()) {
            System.out.println(s);
        }
        System.out.println("测试git");
    }
}
