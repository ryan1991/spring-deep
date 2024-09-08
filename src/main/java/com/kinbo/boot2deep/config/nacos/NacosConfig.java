package com.kinbo.boot2deep.config.nacos;

import com.alibaba.boot.nacos.config.properties.NacosConfigProperties;
import com.alibaba.boot.nacos.config.util.NacosPropertiesBuilder;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.spring.factory.CacheableEventPublishingNacosServiceFactory;
import com.alibaba.nacos.spring.factory.NacosServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author songjunbao
 * @date 2024-09-05
 */
@Configuration
public class NacosConfig {

//    @Value(value = "${nacos.config.server-addr}")
//    private String serverAddr;

    @Autowired
    private NacosConfigProperties nacosConfigProperties;


//    @Bean
    public ConfigService configService(){
        CacheableEventPublishingNacosServiceFactory singleton = CacheableEventPublishingNacosServiceFactory.getSingleton();
        Properties globalProps = NacosPropertiesBuilder.buildNacosProperties(nacosConfigProperties.getServerAddr(), nacosConfigProperties.getNamespace(),
                nacosConfigProperties.getEndpoint(), nacosConfigProperties.getSecretKey(), nacosConfigProperties.getAccessKey(),
                nacosConfigProperties.getRamRoleName(), nacosConfigProperties.getConfigLongPollTimeout(),
                nacosConfigProperties.getConfigRetryTime(), nacosConfigProperties.getMaxRetry(),
                nacosConfigProperties.isEnableRemoteSyncConfig());

        ConfigService configService ;
        try {
            configService = singleton.createConfigService(globalProps);
        } catch (NacosException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }

//        ConfigService configService;
//        try {
//            configService = NacosFactory.createConfigService(serverAddr);
//        } catch (NacosException e) {
//            e.printStackTrace();
//            throw new IllegalArgumentException(e);
//        }
        return configService;
    }



}
