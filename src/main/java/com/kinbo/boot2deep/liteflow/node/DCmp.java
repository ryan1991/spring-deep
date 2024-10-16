package com.kinbo.boot2deep.liteflow.node;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;

/**
 * @author songjunbao
 * @date 2024-10-15
 */
@LiteflowComponent("d")
public class DCmp extends NodeSwitchComponent {
    @Override
    public String processSwitch() throws Exception {
        System.out.println("d do...");
        return "t1";
    }
}
