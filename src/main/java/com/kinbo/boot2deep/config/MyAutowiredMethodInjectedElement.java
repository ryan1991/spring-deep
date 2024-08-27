package com.kinbo.boot2deep.config;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * 方法注入@MyAutowired
 * @author songjunbao
 * @date 2024-08-26
 */
public class MyAutowiredMethodInjectedElement extends InjectionMetadata.InjectedElement {

    private Method method;
    private MyAutowired myAutowired;
    private AutowiredBeanBuilder<MyAutowired> builder;

    MyAutowiredMethodInjectedElement(Method method, PropertyDescriptor pd, MyAutowired myAutowired, AutowiredBeanBuilder<MyAutowired> builder) {
        super(method, pd);
        this.method = method;
        this.myAutowired = myAutowired;
        this.builder = builder;
    }

    @Override
    protected void inject(Object target, String requestingBeanName, PropertyValues pvs) throws Throwable {
        //获取到注解里的值
        Object autowireBean =  builder.build(myAutowired, pd.getPropertyType());
        ReflectionUtils.makeAccessible(method);
        method.invoke(target, autowireBean);
    }
}
