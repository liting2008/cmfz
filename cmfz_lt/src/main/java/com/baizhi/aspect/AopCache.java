package com.baizhi.aspect;

import com.baizhi.util.SerializeUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.annotation.Annotation;

@Configuration
@Aspect
public class AopCache {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //参数是切点
    @Around("execution(* com.baizhi.service.*.query*(..))")

    //ProceedingJoinPoint 只能用在环绕里
    /*
        查询 （环绕通知）
    * */
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /*有自定注解时*/
        //进行判断，是否有当前注解，如果有切，如果没有，放行
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Annotation annotation = methodSignature.getMethod().getAnnotation(Annotation.class);
        if(annotation!=null){
            System.out.println("说明当前方法有注解，执行切面方法");
            //获取id（namespace）
            String id = proceedingJoinPoint.getTarget().getClass().getName();
            //获取key

            String method = proceedingJoinPoint.getSignature().getName();
            //获取参数
            Object[] args = proceedingJoinPoint.getArgs();
            StringBuilder stringBuilder = new StringBuilder();
            //字符串拼接key
            stringBuilder.append(method);
            //然后拼接参数
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                stringBuilder.append(arg);
            }
            String key = stringBuilder.toString();
            //得到值
            String value = (String) stringRedisTemplate.opsForHash().get(id, key);
            //如果缓存中没有值则，添加
            if(value==null){
                System.out.println("缓存中没有值");
                Object proceed = proceedingJoinPoint.proceed();
                System.out.println("存入缓存");
                stringRedisTemplate.opsForHash().put(id,key, SerializeUtils.serialize(proceed));
                //返回放行
                return proceed;
            }else{
                System.out.println("缓冲中有值直接拿");
                //把字符串反序列化成对象或集合
                return SerializeUtils.serializeToObject(value);
            }
        }else{
            System.out.println("当前方法没有注解，不存入缓存");
            return proceedingJoinPoint.proceed();
        }


      /*  //获取id（namespace）
        String id = proceedingJoinPoint.getTarget().getClass().getName();
        //获取key

        String method = proceedingJoinPoint.getSignature().getName();
        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder();
        //字符串拼接key
        stringBuilder.append(method);
        //然后拼接参数
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            stringBuilder.append(arg);
        }
        String key = stringBuilder.toString();
        //得到值
        String value = (String) stringRedisTemplate.opsForHash().get(id, key);
        //如果缓存中没有值则，添加
        if(value==null){
            System.out.println("缓存中没有值");
            Object proceed = proceedingJoinPoint.proceed();
            System.out.println("存入缓存");
            stringRedisTemplate.opsForHash().put(id,key, SerializeUtils.serialize(proceed));
            //返回放行
            return proceed;
        }else{
            System.out.println("缓冲中有值直接拿");
            //把字符串反序列化成对象或集合
                return SerializeUtils.serializeToObject(value);
        }*/
    }
    @After("execution(* com.baizhi.service.*.*(..))&& !execution(* com.baizhi.service.*.query*(..))")
    /*
    * 修改（后置通知）
    * */
    public void after(JoinPoint joinPoint){
        System.out.println("清空当前namespace下的所有缓存");
        String id = joinPoint.getTarget().getClass().getName();
        stringRedisTemplate.delete(id);
    }
}
