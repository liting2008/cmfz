package com.baizhi;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdmin {
        @Autowired
        private AdminService adminService;
        @Test
        public void test1(){
            List<Admin> list = adminService.queryAll();
            for (Admin admin : list) {
                System.out.println(admin);
            }
        }
        @Test
    public void test2(){
            //测试mybatis缓存
           // adminService.addAdmin(new Admin(UUID.randomUUID().toString(),"123456","123456"));

            //测试切面
            Admin admin = adminService.queryOne("1");
            System.out.println(admin);
        }
}
