package com.baizhi.dao;

import com.baizhi.entity.Admin;

import java.util.List;

public interface AdminDao {
    List<Admin> selectAll();
    Admin selectAdminByUserName(String username);
    void insertAdmin(Admin admin);
}
