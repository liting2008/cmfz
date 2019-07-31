package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gender implements Serializable {
    //男或女的人数
    private String value;
    //省份的名称
    private String name;
}
