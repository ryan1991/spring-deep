package com.kinbo.boot2deep.reference;


import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Component;

/**
 * java客户端泛化调用go dubbo服务端(dubbo协议 hession序列化)
 *
 * @author songjunbao
 * @date 2024-08-07
 */
@Component
public class HelloServiceGenericAdapter {

//    @Resource
//    private ApplicationConfig applicationConfig;


    public  String sayHello(String name){
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
//        reference.setApplication();
        reference.setInterface("com.kinbo.boot2deep.reference.HelloService");
        reference.setGeneric("true");
        reference.setCheck(false);
        GenericService genericService = reference.get();

        Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{name});
        System.out.println("Result: " + result);
        return String.valueOf(result);
    }


}
