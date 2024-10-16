package com.kinbo.boot2deep.config.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 使用jdk动态代理的方式，为标注@FeignClient注解的接口创建对象
 *
 * @author songjunbao
 * @date 2024-01-11
 */
//带泛形 不需要表示注解
//@Component
public class FeignClientFactoryBean<T> implements FactoryBean<T> {


    private String url;

    private Class<?> feignInterface;

    @Override
    public T getObject() throws Exception {

        T feignClient = (T) Proxy.newProxyInstance(feignInterface.getClassLoader(), new Class[]{feignInterface}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("默认使用okHttp调用:" + url + ",参数:" + Arrays.toString(args));
                return "success";
            }
        });

        return feignClient;
    }

    @Override
    public Class<?> getObjectType() {
        return feignInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Class<?> getFeignInterface() {
        return feignInterface;
    }

    public void setFeignInterface(Class<?> feignInterface) {
        this.feignInterface = feignInterface;
    }
}
