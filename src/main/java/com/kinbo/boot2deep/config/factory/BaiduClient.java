package com.kinbo.boot2deep.config.factory;

/**
 * @author songjunbao
 * @date 2024-01-11
 */

@FeignClient(url = "www.baidu.com")
public interface BaiduClient {

    String send(String msg);
}
