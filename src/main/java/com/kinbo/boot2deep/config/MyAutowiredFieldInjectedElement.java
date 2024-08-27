package com.kinbo.boot2deep.config;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author songjunbao
 * @date 2024-08-26
 */
public class MyAutowiredFieldInjectedElement extends InjectionMetadata.InjectedElement {

    private Field field;
    private MyAutowired myAutowired;
    private AutowiredBeanBuilder<MyAutowired> builder;


    MyAutowiredFieldInjectedElement(Field field, MyAutowired myAutowired, AutowiredBeanBuilder<MyAutowired> builder) {
        super(field, null);
        this.field = field;
        this.myAutowired = myAutowired;
        this.builder = builder;
    }

    @Override
    protected void inject(Object target, String requestingBeanName, PropertyValues pvs) throws Throwable {
        //获取到注解里的值
        Object autowiredBean = builder.build(myAutowired, field.getType());
        ReflectionUtils.makeAccessible(field);
        field.set(target, autowiredBean);
    }
}
