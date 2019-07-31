package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Address;
import com.baizhi.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEasypoi {
    @Autowired
    private UserDao userDao;
    @Test
    public void test1()throws Exception{
        List<User> users = userDao.selectAll();
        //由于address属性是临时加的，数据库没数据，临时添加
        List <Address> addresses = new ArrayList<>();
        addresses.add(new Address("1","河北省","保定市"));
        addresses.add(new Address("2","山东省","青岛市"));
        addresses.add(new Address("3","山西省","太原市"));
        addresses.add(new Address("4","云南省","大理市"));
        for (User user : users) {
            user.setAddresses(addresses);
            user.setProfile("D:\\ideaworkspace\\cmfz\\cmfz_lt\\src\\main\\webapp\\img\\1.png");
        }
        //生成Excel代码如下
        //1标题（不是文件名也不是表名）2表名3导出对象4数据库查出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有用户","用户表"),
                User .class, users);
        workbook.write(new FileOutputStream("E:/testPOI/第一个EasyPOIExcel文档.xls"));
        workbook.close();
       // 这样我们就得到的一个java中的Excel,然后把这个输出就得到我们的Excel了
    }
}
