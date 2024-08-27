package com.kinbo.boot2deep.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author songjunbao
 * @date 2024-07-29
 */
public abstract class JsonUtils {


    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);


    public static String convert2Json(Object o){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("对象转json失败", e);
        }
        log.info("json str :{}", jsonStr);
        return jsonStr;
    }



}
