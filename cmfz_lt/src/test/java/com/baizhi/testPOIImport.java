package com.baizhi;

import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testPOIImport {
    @Test
    public void test1()throws Exception{
        //创建一个workbook去接收
        Workbook workbook = new HSSFWorkbook(new FileInputStream("E:/testPOI/第一个Excel文档.xls"));
        //获取表--知道名字可以用getSheet(名字)
        Sheet sheet  = workbook.getSheetAt(0);
        //获取行(不知道有多少行可以通过lastrownum方法先获取一共多少行)
        int lastRowNum = sheet.getLastRowNum();
        //创建集合去接收对象
        List<User> users = new ArrayList<>();
        for (int i = 0; i < lastRowNum; i++) {
            User user = new User();
            //获取每一行
            Row row = sheet.getRow(i+1);
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
}
