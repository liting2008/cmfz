package com.baizhi.dao;

import java.util.List;

public interface BaseDao<T> {
    List<T> selectAll(Integer begin,Integer rows);
    Integer selectRecords();
    void insert(T t);
}
