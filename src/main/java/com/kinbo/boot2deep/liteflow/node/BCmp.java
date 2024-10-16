package com.kinbo.boot2deep.liteflow.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

import java.util.concurrent.TimeUnit;

/**
 * @author songjunbao
 * @date 2024-09-30
 */
@LiteflowComponent("b")
public class BCmp extends NodeComponent {
    @Override
    public void process() throws Exception {
//        TimeUnit.SECONDS.sleep(10);
        System.out.println("b do...");
    }
}
