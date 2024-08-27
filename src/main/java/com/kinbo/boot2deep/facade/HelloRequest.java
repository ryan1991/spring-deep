package com.kinbo.boot2deep.facade;

import java.io.Serializable;

/**
 * @author songjunbao
 * @date 2024-08-09
 */
public class HelloRequest implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
