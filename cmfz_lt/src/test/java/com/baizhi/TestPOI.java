package com.baizhi;

import com.baizhi.dao.UserDao;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPOI {
        @Test
        public void  test1() throws Exception{
            //创建工作簿
            Workbook workbook = new HSSFWorkbook();
            //创建一张表
            Sheet sheet = workbook.createSheet("第一张表");
            //创建行（从0开始）
            Row row = sheet.createRow(0);
            //创建单元格
            Cell cell = row.createCell(0);
            //设置一个居中的单元格样式
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            //创建一个红色的样式
            CellStyle redstyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setColor(Font.COLOR_RED);
            redstyle.setFont(font);
            //将样式添加给单元格(两个样式同时存在，后面的样式会覆盖前面的样式)
            cell.setCellStyle(redstyle);
            //cell.setCellStyle(cellStyle);
            //单元格添加数据
            cell.setCellValue("电话");
            //输出到硬盘
            //如果文件夹不存在创建文件夹(不创建会报找不到文件夹异常)
            File file = new File("E:/testPOI/");
            if(!file.exists()){
                file.mkdir();
            }
            //设置一个单元格2
            Cell cell1 = row.createCell(1);
            //设置一个日期
            cell1.setCellValue(new Date());
            //设置一个日期格式样式
            CellStyle datestyle = workbook.createCellStyle();
            DataFormat dataFormat = workbook.createDataFormat();
            //选string类型
            short format = dataFormat.getFormat("yyyy-MM-dd");
            datestyle.setDataFormat(format);
            //将日期样式添加给单元
             cell1.setCellStyle(datestyle);

             //设置单元格列宽(表里的方法)
            sheet.setColumnWidth(1,5000);
            workbook.write(new FileOutputStream("E:/testPOI/第一个Excel文档.xls"));
            //释放资源
            workbook.close();
        }
        @Autowired
        private UserDao userDao;
        @Test
        public void test2(){
            List<Integer> count = userDao.selectMonth();
            for (Integer integer : count) {
                System.out.println(integer);
            }
        }
}
