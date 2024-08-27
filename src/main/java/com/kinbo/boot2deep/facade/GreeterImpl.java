package com.kinbo.boot2deep.facade;

import com.kinbo.boot2deep.facade.dto.SampleApi;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 注意： IDL protobuf支持跨语言
 *
 * @author songjunbao
 * @date 2024-08-09
 */
@DubboService(protocol = "tri")
public class GreeterImpl implements Greeter {



    @Override
    public SampleApi.Hello2Reply greet(SampleApi.Hello2Request helloRequest) {
        String message = "Hello, " + helloRequest.getName() + "!";
        System.out.println("message:" + message);
        SampleApi.Hello2Reply helloReply =  SampleApi.Hello2Reply.newBuilder().setMessage(message).build();
        return helloReply;
    }
}
