package com.kinbo.boot2deep.controller;

import com.kinbo.boot2deep.config.MyAutoAwareBean;
import com.kinbo.boot2deep.config.MyAutowired;
import com.kinbo.boot2deep.config.factory.BaiduClientAdaptor;
import com.kinbo.boot2deep.config.factory.PddClientAdaptor;
import com.kinbo.boot2deep.service.EchoService;
import com.kinbo.boot2deep.service.check.CompositeCheckService;
import com.kinbo.boot2deep.service.event.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EchoController {


    @Autowired
    private EchoService echoService;

    @Autowired
    private CompositeCheckService checkService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private BaiduClientAdaptor baiduClientAdaptor;

    @Autowired
    private PddClientAdaptor pddClientAdaptor;

    @MyAutowired(value = "song")
    private MyAutoAwareBean myAutoAwareBean;

    @RequestMapping("/echo/{message}")
    public String echo(@PathVariable String message) {
        System.out.println(myAutoAwareBean.getParam());
        try {

            Thread.sleep(50L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return echoService.echo(message);
    }

    @RequestMapping("/echoParam")
    public String echoParam(String message){

        return echoService.echo(message);
    }

    @RequestMapping("/check")
    public String check(){
        checkService.check();
        return "success";
    }

    @RequestMapping("/configChange")
    public void configChange(){
        configService.subscribeChange();
    }


    @RequestMapping("/sendMsgToBaidu/{message}")
    public String sendMsgToBaidu(@PathVariable String message){
        String result = baiduClientAdaptor.send(message);
        return result;
    }

    @RequestMapping("/sendMsgToPdd/{message}")
    public String sendMsgToPdd(@PathVariable String message){
        String result = pddClientAdaptor.call(message);
        return result;
    }


}
