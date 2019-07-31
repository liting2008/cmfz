package com.baizhi.controller;

import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    //分页查询
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> map = carouselService.queryAll(page, rows);
        return map;
    }
    @RequestMapping("edit")
    public String edit(Carousel carousel , String oper , String[] id){
        if ("edit".equals(oper)){
            //执行修改代码
            carouselService.modifyCarousel(carousel);
            String id1 = carousel.getId();
            return id1;
        }else if("add".equals(oper)){
            //执行增加代码
            String s = carouselService.addCarousel(carousel);
            return s;
        }else{
            //执行删除代码
            carouselService.removeCarousel(id);

        }
        return null;
    }
    //文件上传
    @RequestMapping("upload")
    public void upload(String id , MultipartFile imgPath, HttpServletRequest request , HttpServletResponse response){
        String originalFilename = imgPath.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("carouselPic");
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        try {
            imgPath.transferTo(new File(path,originalFilename));
            //修改数据库的路径
            Carousel carousel = new Carousel();
            carousel.setId(id);
            carousel.setImgPath(originalFilename);
            carouselService.modifyImgPath(carousel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("updateStatus")
    public void updateStatus(Carousel carousel){
        carouselService.modifyStatus(carousel);
    }

}
