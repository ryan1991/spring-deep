package com.kinbo.boot2deep.liteflow.executor;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author songjunbao
 * @date 2024-09-30
 */
@Component
public class LiteFlowCommand implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Resource
    private FlowExecutor flowExecutor;


    /**
     * 规则在nacos上
     * @param args
     */
    @Override
    public void run(String... args) {
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", null);
        if (response.isSuccess()){
            log.info("chain1执行成功");
        }else {
            log.info("chain1执行失败");
        }

        LiteflowResponse response2 = flowExecutor.execute2Resp("chain2", null);
        if (response2.isSuccess()){
            log.info("chain2执行成功");
        }else {
            log.info("chain2执行失败");
        }

        LiteflowResponse mainResponse = flowExecutor.execute2Resp("mainChain", null);
        if (mainResponse.isSuccess()){
            log.info("mainChain执行成功");
        }else {
            log.info("mainChain执行失败");
        }
    }
}
