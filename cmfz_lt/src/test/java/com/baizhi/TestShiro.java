package com.baizhi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestShiro {
    @Test
    public void test1(){
        //根据图示可看出，所有操作都在安全管理器里完成的，先获取（可以通过工厂）
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory();
        //获取实例就是安全管理器
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        //指明使用的哪个安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //获取令牌
        UsernamePasswordToken token = new UsernamePasswordToken("1","1");

        //登录
        subject.login(token);
        //查看是否认证成功
        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated);
        //UnknownAccountException 账户异常
        //IncorrectCredentialsException 凭证异常
    }
}
