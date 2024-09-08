package com.kinbo.boot2deep.config.nacos;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.convert.NacosConfigConverter;
import com.kinbo.boot2deep.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * 自定义NacosConfigConverter
 *
 * @author songjunbao
 * @date 2024-09-08
 */
public class UserNacosConfigConverter implements NacosConfigConverter<User> {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserNacosConfigConverter.class);


    @Override
    public boolean canConvert(Class<User> targetType) {
        return true;
    }

    @Override
    public User convert(String config) {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(config));
        } catch (IOException e) {
            LOGGER.error("load properties error：" + config, e);
        }

//        User user = new User();
//        user.setId(Long.parseLong(properties.getProperty("id")));
//        user.setAge(Integer.parseInt(properties.getProperty("age")));
//        user.setName(properties.getProperty("name"));

        String jsonStr = JSON.toJSONString(properties);
        //json类型
        return JSON.parseObject(jsonStr, User.class);
    }
}
