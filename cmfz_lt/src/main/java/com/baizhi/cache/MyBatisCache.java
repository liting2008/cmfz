/*package com.baizhi.cache;

import com.baizhi.util.SerializeUtils;
import com.baizhi.util.SpringContextUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;


import com.baizhi.util.SerializeUtils;
import com.baizhi.util.SpringContextUtil;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;

public class MyBatisCache implements Cache {
    //id属性就是namespace，也就是service类
    private String id;
    //有参构造
    public MyBatisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("向缓存中添加值");
        //通过工具类获取
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
        //键值对中存键值对
        stringRedisTemplate.opsForHash().put(id,key.toString(), SerializeUtils.serialize(value));
    }

    @Override
    public Object getObject(Object key) {
        //首先来这里查询
        System.out.println("来缓存中查询");
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
        String   value = (String) stringRedisTemplate.opsForHash().get(id, key.toString());
        if(value==null){
            System.out.println("没有从缓存中获取值");
            return null;
        }else{
            //返回的是对象或集合，所以要把字符串反序列化对象或集合
            System.out.println("从缓存中取到了值");
            return SerializeUtils.serializeToObject(value);
        }
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        //修改会进入这个方法
        System.out.println("修改了数据库，清空当前namespace缓存");
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
        stringRedisTemplate.delete(id);
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
*/