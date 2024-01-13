package com.kinbo.boot2deep;

import com.kinbo.boot2deep.config.ExampleProperties;
import com.kinbo.boot2deep.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableConfigurationProperties(ExampleProperties.class)
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
