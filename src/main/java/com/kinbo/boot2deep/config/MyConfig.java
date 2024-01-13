package com.kinbo.boot2deep.config;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

/**
 * @author songjunbao
 * @date 2023-12-07
 */
public class MyConfig {
    private String name;
    private int age;

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

    public MyConfig(Environment environment){
        Binder binder = Binder.get(environment);
        this.name = binder.bind("myconfig.name", String.class).orElse("zs");
        this.age = binder.bind("myconfig.age", Integer.class).orElse(0);

    }

    @Override
    public String toString() {
        return "MyConfig{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
