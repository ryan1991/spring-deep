package com.kinbo.boot2deep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author songjunbao
 * @date 2023-12-07
 */
@Configuration
public class AppConfig {

    @Autowired
    private Environment environment;


    @Bean
    public MyConfig myConfig(Environment environment){
        return new MyConfig(environment);
    }

//    @Bean
//    public ExampleProperties exampleProperties(){
//       ExampleProperties exampleProperties = Binder.get(environment)
//               .bind("example", Bindable.of(ExampleProperties.class))
//               .get();
//       return exampleProperties;
//    }

    @Bean
    public MyAutowiredAnnotationProcessor myAutowiredAnnotationProcessor(){
        return new MyAutowiredAnnotationProcessor();
    }


    @Bean
    public MyAutoAwareBean myAutoAwareBean(){
        MyAutoAwareBean myAutoAwareBean = new MyAutoAwareBean();
        myAutoAwareBean.setParam("hello");
        return myAutoAwareBean;
    }
}
