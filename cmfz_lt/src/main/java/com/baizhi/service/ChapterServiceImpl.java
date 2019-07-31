package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
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
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows , String albumId) {
        Map<String , Object> map = new HashMap<>();
        Integer records = chapterDao.selectRecords();
        Integer total = records%rows == 0 ?records/rows : records/rows+1;
        Integer begin = (page-1)*rows;
        List<Chapter> carousels = chapterDao.selectAll(begin, rows,albumId);
        //当前页
        map.put("page",page);
        //总记录数
        map.put("records",records);
        //总页数
        map.put("total",total);
        //查询出的集合
        map.put("rows",carousels);
        return map;
    }

    @Override
    public String addChapter(Chapter chapter) {
        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        chapterDao.insert(chapter);
        return s;
    }

    @Override
    public void modifyDownpath(Chapter chapter) {
        chapterDao.updateDownpath(chapter);
    }
}
