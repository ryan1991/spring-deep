package com.kinbo.boot2deep.liteflow.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @author songjunbao
 * @date 2024-09-30
 */
@LiteflowComponent("c")
public class CCmp extends NodeComponent {


    @Override
    public void process() throws Exception {

        System.out.println("c do...");
    }
}
