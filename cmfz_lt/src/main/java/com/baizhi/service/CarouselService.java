package com.baizhi.service;

import com.baizhi.entity.Carousel;

import java.util.Map;

public interface CarouselService {
    Map<String,Object> queryAll(Integer page,Integer rows);
    String addCarousel(Carousel carousel);
    //修改图片路径方法
    void modifyImgPath(Carousel carousel);

    void removeCarousel(String[] id);
    //修改轮播图状态
    String modifyStatus(Carousel carousel);
    //修改轮播图
    void modifyCarousel(Carousel carousel);
}

