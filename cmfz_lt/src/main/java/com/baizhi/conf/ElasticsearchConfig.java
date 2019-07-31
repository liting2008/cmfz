package com.baizhi.conf;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ElasticsearchConfig {
    @PostConstruct
    void init(){
        //netty冲突
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}
