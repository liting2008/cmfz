package com.baizhi;

import com.baizhi.annotation.UserAnnotation;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBestPOI {
    @Autowired
    private UserDao userDao;
    @Test
    public void test1()throws Exception{
        List<User> users = userDao.selectAll();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("用户表");
        Row row = sheet.createRow(0);
        //用自定义注解就不用下面这种方法了
       /* String[] titles = {"编号","电话","密码","创建时间"};
        //遍历数组给表的第一行赋值标题
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            //创建单元格
            Cell cell = row.createCell(i);
            //给单元格添加数据
            cell.setCellValue(title);
        }*/

       //获取类对象
        Class<User> userClass = User.class;
/*        //获取所有公开属性（public修饰的）
        userClass.getFields();*/
        //获取所有属性
        Field[] declaredFields = userClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            //获取对象的属性
            Field declaredField = declaredFields[i];
            System.out.println(declaredField.getName()+"=================");
            UserAnnotation annotation = declaredField.getAnnotation(UserAnnotation.class);
            //获取注解的名字
            String name = annotation.name();
            System.out.println(name);
            Cell cell = row.createCell(i);
            cell.setCellValue(name);
        }

        //遍历用户（从第二行开始每一行是一个用户，每一个单元格是一个属性）
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            //从第二行创建用户
            Row row1 = sheet.createRow(i + 1);
            Cell cell = row1.createCell(0);
            cell.setCellValue(user.getId());
            Cell cell1 = row1.createCell(1);
            cell1.setCellValue(user.getPhone());
            Cell cell2 = row1.createCell(2);
            cell2.setCellValue(user.getPassword());
            Cell cell3 = row1.createCell(3);
            cell3.setCellValue(user.getRegistTime());
        }

        workbook.write(new FileOutputStream("E:/testPOI/第一个Excel文档.xls"));
        workbook.close();
    }
}
