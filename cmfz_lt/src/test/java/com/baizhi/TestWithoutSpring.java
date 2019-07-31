package com.baizhi;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class TestWithoutSpring {
    @Test
    public void test1(){
        //1密码明文2盐3散列(散列的次数)
        Md5Hash qwer = new Md5Hash("123456", "qwer", 1024);
        //加密后的密文
        String s = qwer.toHex();
        System.out.println(s);
        //6c93325fc9a959026cd070fa4f7cf649 明文为：123456 md5加盐并散列1024次的结果
    }
}
