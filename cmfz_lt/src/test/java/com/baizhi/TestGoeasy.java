package com.baizhi;

import io.goeasy.GoEasy;
import org.junit.Test;
       /* GoEasy专注于服务器与浏览器,浏览器与浏览器之间消息推送,完美兼容世界上的绝大多数浏览器,包括IE6, IE7之类的非常古老的浏览器。
        GoEasy采用 发布/订阅 的消息模式,帮助您非常轻松的实现一对一,一对多的通信。*/
public class TestGoeasy {
    @Test
    public void test1(){

        GoEasy goEasy = new GoEasy("http(s)://rest-hangzhou.goeasy.io", "BC-dff5d88611e94fc0844c638b1264220e");
        goEasy.publish("demo_channel", "Hello world!");
    }
}
