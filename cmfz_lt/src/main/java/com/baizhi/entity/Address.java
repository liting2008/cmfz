package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Excel(name="地址编号")
    private String  id;
    @Excel(name="省份")
    private String province;
    @Excel(name = "城市")
    private String city;
}
