package com.kinbo.boot2deep.config.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.spring.factory.NacosServiceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author songjunbao
 * @date 2024-09-05
 */
@Configuration
public class NacosConfig {

    @Value(value = "${nacos.config.server-addr}")
    private String serverAddr;


    @Bean
    public ConfigService configService(){
        ConfigService configService;
        try {
            configService = NacosFactory.createConfigService(serverAddr);
        } catch (NacosException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
        return configService;
    }

}
