package com.kinbo.boot2deep.controller;

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigChangeEvent;
import com.alibaba.nacos.api.config.ConfigChangeItem;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import com.alibaba.nacos.client.config.listener.impl.AbstractConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author songjunbao
 * @date 2024-09-05
 */

@RestController
@RequestMapping("/config")
public class ConfigController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);

    private final static String DATA_ID = "boot2-deep";

    @Autowired
    private ConfigService configService;

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;



    @GetMapping("/get")
    public boolean get(){
        return useLocalCache;
    }

    @PostConstruct
    public void init() throws NacosException {
        configService.addListener(DATA_ID, Constants.DEFAULT_GROUP, new AbstractConfigChangeListener() {

            @Override
            public void receiveConfigChange(ConfigChangeEvent event) {
                Collection<ConfigChangeItem> changeItems = event.getChangeItems();
                for (ConfigChangeItem changeItem : changeItems){
                    LOGGER.info("receiveConfigInfo changeItem:{}", changeItem);
                }
            }
        });

    }


    @GetMapping("/getCfgStr")
    public Properties getCfgStr() throws NacosException {
        String configStr = configService.getConfig("boot2-deep", Constants.DEFAULT_GROUP, 20000);
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(configStr));
        } catch (IOException e) {
            LOGGER.error("load properties error：" + configStr, e);
        }
        return properties;
    }


    //参数类型只能String ?
    @NacosConfigListener(dataId = "boot2-deep", type = ConfigType.PROPERTIES)
    public void onMessage(String config){
        LOGGER.info("config changed:{}" + config);
    }

}
