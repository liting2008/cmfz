package com.baizhi.service;
import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselDao carouselDao;
    @Override
    @Transactional(propagation =Propagation.SUPPORTS)
    //分页查询
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String,Object> map = new HashMap();
        Integer records = carouselDao.selectRecords();
        Integer total= records%rows==0?records/rows:records/rows+1;
        Integer begin = (page-1)*rows;
        List<Carousel> carousels = carouselDao.selectAll(begin, rows);
        //page当前页
        map.put("page",page);
        //总记录数
        map.put("records",records);
        //总页数
        map.put("total",total);
        //查出的集合
        map.put("rows",carousels);
        return map;
    }
    //添加
    @Override
    public String addCarousel(Carousel carousel) {
        String s = UUID.randomUUID().toString();
        carousel.setId(s);
        carouselDao.insert(carousel);
        return s;
    }
    //修改文件路径
    @Override
    public void modifyImgPath(Carousel carousel) {
        carouselDao.updateImgPath(carousel);
    }
    //删除
    @Override
    public void removeCarousel(String[] id) {
        for (String s : id) {
            carouselDao.deleteCarousel(s);
        }
    }
    //修改状态
    @Override
    public String modifyStatus(Carousel carousel) {
        if(carousel.getStatus().equals("正常")){
            carousel.setStatus("冻结");
            carouselDao.updateStatus(carousel);
        }else{
            carousel.setStatus("正常");
            carouselDao.updateStatus(carousel);
        }
        return  carousel.getId();
    }

    @Override
    public void modifyCarousel(Carousel carousel) {
        carouselDao.updateCarousel(carousel);
    }
}
