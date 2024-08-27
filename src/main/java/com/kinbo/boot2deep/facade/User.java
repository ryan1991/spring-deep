package com.kinbo.boot2deep.facade;

import java.io.Serializable;
import java.util.Date;

/**
 * @author songjunbao
 * @date 2024-08-07
 */
public class User implements Serializable {

    private String id;

    private String name;

    private int age;

    private Date time = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
