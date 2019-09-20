package com.bend.his.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * 客户端调用配置-公卫
 */
@Configuration
@PropertySource("classpath:his-public.properties")
public class HISPublicWSClientConfig {

    @Value("${WebServicePublicHost}")
    private String defaultURI;

    /*
     * 创建bean
     */
    @Bean
    public HISPublicWSClient hisPublicWSClient(Jaxb2Marshaller marshaller) {
        HISPublicWSClient client = new HISPublicWSClient();
        //默认对应的ws服务地址 client请求中还能动态修改的
//        client.setDefaultUri("http://47.111.29.88:11008/WebService.asmx");
        client.setDefaultUri(defaultURI);
        client.setMarshaller(marshaller);//指定转换类
        client.setUnmarshaller(marshaller);
        return client;
    }
}