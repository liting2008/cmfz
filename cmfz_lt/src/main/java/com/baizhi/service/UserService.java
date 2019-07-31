package com.baizhi.service;

import com.baizhi.entity.Carousel;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> queryAll(Integer page,Integer rows);
    List<Integer> queryMonth();
    //修改图片路径方法
    void modifyImgPath(User user);
    //用户注册
    Map<String,Object> regist(User user);
    //修改用户状态
    String modifyStatus(User user);
    //第二版柱状图每月注册人数
    Map<String,Object> queryMonthAndCount(String gender);
}
