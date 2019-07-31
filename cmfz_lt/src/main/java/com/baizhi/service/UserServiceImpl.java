package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.MonthAndCount;
import com.baizhi.entity.User;
import com.baizhi.util.MD5Utils;
import com.google.gson.Gson;
import io.goeasy.GoEasy;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String,Object> map = new HashMap<>();
        Integer begin = (page-1)*rows;
        List carousels = userDao.selectAll(begin, rows);
        Integer records = userDao.selectRecords();
        Integer total = records%rows==0?records/rows:records/rows+1;
        //当前页
        map.put("page",page);
        //每页轮播图条数
        map.put("rows",carousels);
        //总页数
        map.put("total",total);
        //总条数
        map.put("records",records);

        return map;
    }
    //第一版（写死月份）的柱状图
    @Override
    public List<Integer> queryMonth() {
        List<Integer> count = userDao.selectMonth();
        return count;
    }
    //第二版柱状图


    @Override
    public void modifyImgPath(User user) {
        userDao.updateImgPath(user);
    }

    @Override
    public Map<String,Object> regist(User user) {
       //判断该用户是否已经注册
        User user1 = userDao.selectByPhone(user.getPhone());
        Map<String, Object> map= new HashMap<>();
        if(user1!=null){
            //该用户已存在，返回状态值
            map.put("error",-400);
            map.put("message","手机号已存在");
            return map;
        }else{
            //注册成功，展示用户信息
            //User user2 = userDao.selectByPhone(user.getPhone());
            user.setId(UUID.randomUUID().toString());
            String salt = MD5Utils.getSalt();
            user.setSalt(salt);
            String password = MD5Utils.getPassword(user.getPassword() + salt);
            user.setPassword(password);
            user.setRegistTime(new Date());
            userDao.regist(user);
            map.put("userId",user.getId());
            map.put("phone",user.getPhone());
            map.put("password",user.getPassword());
            map.put("salt",user.getSalt());
            map.put("dharmaName",user.getDharmaName());
            map.put("province",user.getProvince());
            map.put("city",user.getCity());
            map.put("gender",user.getGender());
            map.put("personalSign",user.getPersonalSign());
            map.put("profile",user.getProfile());
            map.put("status",user.getStatus());
            map.put("registTime",user.getRegistTime());

            //调用这个方法里的实时更新
            queryMonthAndCount(user.getGender());
            return map;
        }
    }
    //修改用户状态

    @Override
    public String modifyStatus(User user) {
        if(user.getStatus().equals("正常")){
            user.setStatus("冻结");
           userDao.updateStatus(user);
        }else{
            user.setStatus("正常");
            userDao.updateStatus(user);
        }
        return  user.getId();
    }
    //柱状图和地图
    @Override
    public Map<String,Object> queryMonthAndCount(String gender) {
        Map<String,Object> map = new HashMap<>();
        //查出有人注册的月份和人数
        List<MonthAndCount> list = userDao.selectMonthAndCount();
        //将月份和人数分别放入两个集合
        List month = new ArrayList();
        List count = new ArrayList();
        for (MonthAndCount monthAndCount : list) {
              month.add(monthAndCount.getMonth());
              count.add(monthAndCount.getCount());
        }
        map.put("month",month);
        map.put("count",count);


        map.put("man",userDao.selectByGender("男"));
        map.put("women",userDao.selectByGender("女"));

        Gson gson = new Gson();
        String s = gson.toJson(map);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-5a129bc32c42435db40f84691bea58d5");
        goEasy.publish("demo_channel",s);
        return map;
    }
}
