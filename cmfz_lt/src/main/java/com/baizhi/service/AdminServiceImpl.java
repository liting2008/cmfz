package com.baizhi.service;

import com.baizhi.annotation.AopAnnotation;
import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @AopAnnotation
    public List<Admin> queryAll() {
        List<Admin> list = adminDao.selectAll();
        return list;
    }

    @Override
    public Map<String, Object> login(Admin admin) {
        Admin admin1 = adminDao.selectAdminByUserName(admin.getUsername());
        Map<String,Object> map = new HashMap<>();
        if(admin1==null){
            map.put("code",300);
            map.put("message","用户名不存在");
        }else if(admin1.getPassword().equals(admin.getPassword())){
            map.put("code",200);
            map.put("message","登录成功");
        }else{
            map.put("code",400);
            map.put("message","密码错误");
        }
        return map;
    }

    @Override
    public Admin queryOne(String username) {
        Admin admin = adminDao.selectAdminByUserName(username);
        return admin;
    }

    @Override
    public void addAdmin(Admin admin) {
        adminDao.insertAdmin(admin);
    }


}
