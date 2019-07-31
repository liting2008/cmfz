package com.baizhi;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzLtApplicationTests {
    @Autowired
    AdminService adminService;
    @Test
    public void contextLoads() {
        List<Admin> list = adminService.queryAll();
        for (Admin admin : list) {
            System.out.println(admin);
        }
    }
    @Test
    public void test2(){
        Map<String, Object> map = adminService.login(new Admin("1","1", "1","1"));
        System.out.println(map);
    }

    @Autowired
    UserDao userDao;
    @Test
    public void test3(){
        List<User> list = userDao.selectAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

}
