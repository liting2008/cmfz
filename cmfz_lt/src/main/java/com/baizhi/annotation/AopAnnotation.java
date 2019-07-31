package com.baizhi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//什么时候用
@Retention(RetentionPolicy.RUNTIME)
//注解使用的地方
@Target(ElementType.METHOD)
public @interface AopAnnotation {
    //根据用户具体要求什么方法要缓存，什么方法不要缓存，来添加注解
    //比如queryAll要加缓存，就去service实现类上添加@AopAnnotation  ,再然后去AopCache看是否有这个注解
}
