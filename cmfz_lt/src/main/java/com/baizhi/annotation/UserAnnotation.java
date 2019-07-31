package com.baizhi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//声明自定义注解使用的时机，运行时使用
@Retention(RetentionPolicy.RUNTIME)
//声明自定义注解使用的地方(此处用在属性上)
@Target(ElementType.FIELD)
public @interface UserAnnotation {
    public String name();
}
