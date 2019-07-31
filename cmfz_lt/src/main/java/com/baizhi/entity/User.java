package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baizhi.annotation.UserAnnotation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Excel(name = "用户编号")
   // @UserAnnotation(name="用户编号")
    private String id;
    @Excel(name="电话号码")
   // @UserAnnotation(name="电话号码")
    private String phone;
    @Excel(name="用户密码")
    //@UserAnnotation(name="用户密码")
    private String password;
   // @UserAnnotation(name = "盐")
    private String salt;
   // @UserAnnotation(name="上师")
    private String dharmaName;
   // @UserAnnotation(name="省")
    private String province;
   // @UserAnnotation(name="市")
    private String city;
   // @UserAnnotation(name="性别")
    private String gender;
   // @UserAnnotation(name="")
    private String personalSign;
   // @UserAnnotation(name="")
    @Excel(name = "头像",type = 2 ,width = 40 , height = 20)
    private String profile;
    //@UserAnnotation(name="")
    private String status;

   // @UserAnnotation(name = "注册时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间",format = "yyyy-MM-dd")
    private Date registTime;
    //属性是对象用@ExcelEntity,属性是集合用@ExcelCollection()
    @ExcelCollection(name = "所有地址")
    private List<Address> addresses;

}
