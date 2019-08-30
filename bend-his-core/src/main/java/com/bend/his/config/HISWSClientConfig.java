package com.bend.his.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.annotation.Resource;

/**
 * 客户端调用配置
 *
 */
@Configuration
@PropertySource("classpath:his.properties")
public class HISWSClientConfig {

    @Value("${WebServiceHost}")
    private String defaultURI;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        //会扫描此类下面的对应的 jaxb2实体类 因为是使用 Marshaller和 unmarshaller来进行xml和bean直接转换的
        //具体是判断此路径下是否包含 ObjectFactory.class 文件
        //设置 JAXBContext 对象
        marshaller.setContextPath("com.bend.his.wsdl");
        //或者使用 以下方式设置
//        marshaller.setPackagesToScan(packagesToScan);
//        marshaller.setClassesToBeBound(classesToBeBound);
        return marshaller;
    }
    
    /*
     * 创建bean
     */
    @Bean
    public HISWSClient hiswsClient(Jaxb2Marshaller marshaller) {
        HISWSClient client = new HISWSClient();
        //默认对应的ws服务地址 client请求中还能动态修改的
//        client.setDefaultUri("http://47.111.29.88:11008/WebService.asmx");
        client.setDefaultUri(defaultURI);
        client.setMarshaller(marshaller);//指定转换类
        client.setUnmarshaller(marshaller);
        return client;
    }
}