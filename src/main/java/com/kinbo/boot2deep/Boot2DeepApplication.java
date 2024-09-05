package com.kinbo.boot2deep;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.EnableNacos;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.kinbo.boot2deep.config.ExampleProperties;
import com.kinbo.boot2deep.config.MyConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableConfigurationProperties(ExampleProperties.class)
@EnableDubbo
//0.2.2之前的版本需要使用此注解来关联配置
//@NacosPropertySource(dataId = "boot2-deep", autoRefreshed = true)
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "${nacos.config.server-addr}"))
public class Boot2DeepApplication {



    public static void main(String[] args) {

        SpringApplication.run(Boot2DeepApplication.class, args);


    }




    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event){

        System.out.println("当前WebServer实现类为：" + event.getWebServer().getClass().getName());
        MyConfig myConfig = event.getApplicationContext().getBean(MyConfig.class);
        System.out.println("MyConfig：" + myConfig);
        ExampleProperties exampleProperties = event.getApplicationContext().getBean(ExampleProperties.class);
        System.out.println("exampleProperties：" + exampleProperties);
    }
}
