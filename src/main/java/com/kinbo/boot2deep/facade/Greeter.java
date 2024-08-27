package com.kinbo.boot2deep.facade;

import com.kinbo.boot2deep.facade.dto.SampleApi;

/**
 * @author songjunbao
 * @date 2024-08-09
 */
public interface Greeter {

    SampleApi.Hello2Reply greet(SampleApi.Hello2Request helloRequest);
}
