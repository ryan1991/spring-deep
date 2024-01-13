package com.kinbo.boot2deep.config.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author songjunbao
 * @date 2024-01-13
 */
@Service
public class BaiduClientAdaptor {

    @Resource
    private BaiduClient baiduClient;

    public String send(String msg){
        return baiduClient.send(msg);
    }


}
