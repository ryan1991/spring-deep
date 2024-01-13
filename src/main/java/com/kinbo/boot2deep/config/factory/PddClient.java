package com.kinbo.boot2deep.config.factory;

/**
 * @author songjunbao
 * @date 2024-01-13
 */
@FeignClient(url = "www.pdd.com")
public interface PddClient {

    String call(String param);
}
