package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
   List<Admin> queryAll();
  Map<String,Object> login(Admin admin);
  Admin queryOne(String username);
  void addAdmin(Admin admin);
}
