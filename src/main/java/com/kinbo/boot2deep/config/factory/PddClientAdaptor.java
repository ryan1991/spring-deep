package com.kinbo.boot2deep.config.factory;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author songjunbao
 * @date 2024-01-13
 */
@Service
public class PddClientAdaptor {
    @Resource
    private PddClient pddClient;

    public String call(String param){
        return pddClient.call(param);
    }
}
