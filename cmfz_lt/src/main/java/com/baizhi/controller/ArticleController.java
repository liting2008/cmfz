package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    //文件上传到空间
    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile file, HttpServletRequest request){
        String originalFilename = file.getOriginalFilename();
        String articlePic = request.getSession().getServletContext().getRealPath("articlePic");
        File file1 = new File(articlePic);
        if(!file1.exists()){
            file1.mkdir();
        }
        //上传1路径2名称
        Map<String,Object> map = new HashMap();
        try {
            file.transferTo(new File(articlePic,originalFilename));

            map.put("error",0);
            map.put("url","http://localhost:8888/cmfz/articlePic/"+originalFilename);
            return map;
        } catch (IOException e) {
            e.printStackTrace();

            map.put("error",1);
            map.put("url","http://localhost:8888/cmfz/articlePic/"+originalFilename);
            return map;
        }
    }

    //查询空间所有文件
    @RequestMapping("showAll")
    public Map<String,Object> showAll(HttpServletRequest request){
        String articlePic = request.getSession().getServletContext().getRealPath("articlePic");
        File file = new File(articlePic);
        String[] list = file.list();
        Map<String,Object> map = new HashMap<>();
        map.put("current_url","http://localhost:8888/cmfz/articlePic/");
        map.put("total_count",list.length);
        //把lists集合放入map集合
            List<Object> lists = new ArrayList<>();
            //itar 和 iter 集合遍历
            for (int i = 0; i < list.length; i++) {
                String s = list[i];
                Map<String,Object> map1 = new HashMap<>();
                //map和对象打出的json串一样
                map1.put("is_dir",false);
                map1.put("has_file",false);
                //map1中放入文件大小
                File file1 = new File(articlePic,s);
                long length = file1.length();
                map1.put("filesize",length);
                map1.put("is_photo",true);
                //文件类型不能写死，有jpg，png等等(+1是为了去掉后缀前面的"."只要jpg或png)
                String substring = s.substring(s.lastIndexOf(".") + 1);
                map1.put("filetype",substring);
                map1.put("filename",s);
                map1.put("datetime",new Date());
                lists.add(map1);
            }
        map.put("file_list",lists);
        return map;
    }
    @RequestMapping("addArticle")
    public void addArticle(String title , String content){
        System.out.println(title);
        System.out.println(content);
    }
}
