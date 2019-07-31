package com.baizhi.dao;

import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

public interface CarouselDao extends BaseDao<Carousel> {
    //修改路径
    void updateImgPath(Carousel carousel);
    //删除轮播图
    void deleteCarousel(String id);
    //修改轮播图状态
    void updateStatus(Carousel carousel);
    //查一个
    Carousel selectOne(@Param("id") String id);
    //修改轮播图
    void updateCarousel(Carousel carousel);
}
