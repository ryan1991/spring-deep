package com.kinbo.boot2deep.reference;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author songjunbao
 * @date 2024-08-07
 */
@Component
public class HelloServiceAdapter {
    @DubboReference(check = false)
    private HelloService helloService;

    public  String sayHello(String name){
        return helloService.sayHello(name);
    }


}
