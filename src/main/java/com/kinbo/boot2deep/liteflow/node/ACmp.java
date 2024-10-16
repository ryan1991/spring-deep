package com.kinbo.boot2deep.liteflow.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @author songjunbao
 * @date 2024-09-30
 */
@LiteflowComponent("a")
public class ACmp extends NodeComponent {
    @Override
    public void process() throws Exception {

        System.out.println("a.tag:" + this.getTag());
        System.out.println("a do...");
    }
}
