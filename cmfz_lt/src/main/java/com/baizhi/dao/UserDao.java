package com.baizhi.dao;


import com.baizhi.entity.Gender;
import com.baizhi.entity.MonthAndCount;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao extends BaseDao{
    //查询所有
    List<User> selectAll();
    //柱状图每月注册人数
    List<Integer> selectMonth();

    //修改路径
    void updateImgPath(User user);
    //用户注册
    void regist(User user);
    //根据电话查询用户是否已经注册
    User selectByPhone(@Param("phone") String phone);

    //修改用户状态
    void updateStatus(User user);
    //修改用户
    void updateUser(User user);


    //柱状图，显示每个月注册人数
    List<MonthAndCount> selectMonthAndCount();
    //地图，查每个省份的男女人数分布
    List<Gender> selectByGender(String gender);
}

