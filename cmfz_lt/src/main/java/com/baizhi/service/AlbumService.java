package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;

import java.util.Map;

public interface AlbumService {
    Map<String , Object> queryAll(Integer page, Integer rows);
    String addAlbum(Album album);
    //修改图片路径方法
    void modifyCover(Album album);

    void removeAlbum(String[] id);
}
