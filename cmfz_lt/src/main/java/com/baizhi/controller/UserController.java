package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("importFile")
    public void importFile(MultipartFile file) throws Exception {
        Workbook workbook = new HSSFWorkbook(file.getInputStream());
        //获取表--知道名字可以用getSheet(名字)
        Sheet sheet = workbook.getSheetAt(0);
        //获取行(不知道有多少行可以通过lastrownum方法先获取一共多少行)
        int lastRowNum = sheet.getLastRowNum();
        //创建集合去接收对象
        List<User> users = new ArrayList<>();
        for (int i = 0; i < lastRowNum; i++) {
            User user = new User();
            //获取每一行
            Row row = sheet.getRow(i + 1);
            //获取单元格
            Cell cell = row.getCell(0);
            //获取单元格值
            String id = cell.getStringCellValue();
            //添加对象属性
            user.setId(id);
            Cell cell1 = row.getCell(1);
            String phone = cell1.getStringCellValue();
            user.setPhone(phone);
            Cell cell2 = row.getCell(1);
            String password = cell2.getStringCellValue();
            user.setPassword(password);
            users.add(user);
        }
        for (User user : users) {
            System.out.println(user);
        }
    }
    //第一版柱状图
    @RequestMapping("queryMonth")
    public List<Integer> queryMonth() {
        List<Integer> counts = userService.queryMonth();
        for (Integer count : counts) {
            System.out.println(count);
        }
        return counts;
    }
    //第二版柱状图
    @RequestMapping("queryCount")
    public Map<String,Object> queryCount(User user){
        Map<String, Object> map = userService.queryMonthAndCount(user.getGender());
        return map;
    }

    @RequestMapping("queryAll")
    public Map<String, Object> queryAll(Integer page, Integer rows) {

        Map<String, Object> map = userService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public String edit(User user, String oper, String[] id) {
        if ("edit".equals(oper)) {
            //执行修改代码
            // userService.modifyImgPath(user);
        } else if ("add".equals(oper)) {
            //执行增加代码
            // String s = userService.addCarousel(user);
            //return s;
        } else {
            //执行删除代码
            //userService.removeCarousel(id);
            return null;
        }
        return null;
    }

    @RequestMapping("upload")
    public void upload(String id, MultipartFile profile, HttpServletRequest request, HttpServletResponse response) {
        //文件上传
        String originalFilename = profile.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("carouselPic");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            profile.transferTo(new File(path, originalFilename));
            //修改数据库的路p径
            User user = new User();
            user.setId(id);
            user.setProfile(originalFilename);
            userService.modifyImgPath(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //用户注册
    @RequestMapping("regist")
    public Map<String, Object> regist(User user) {
        Map<String, Object> map = userService.regist(user);
        return map;
    }
    //修改用户状态
    @RequestMapping("modifyStatus")
    public String  modifyStatus(User user) {
        String s = userService.modifyStatus(user);
        return s;
    }
}