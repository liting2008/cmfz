package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    Map<String , Object> queryAll(Integer page, Integer rows, String albumId);
    String addChapter(Chapter chapter);
    //修改图片路径方法
    void modifyDownpath(Chapter chapter);
}
