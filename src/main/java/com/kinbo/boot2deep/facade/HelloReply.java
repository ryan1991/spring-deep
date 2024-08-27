package com.kinbo.boot2deep.facade;

import java.io.Serializable;

/**
 * @author songjunbao
 * @date 2024-08-09
 */
public class HelloReply implements Serializable {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
