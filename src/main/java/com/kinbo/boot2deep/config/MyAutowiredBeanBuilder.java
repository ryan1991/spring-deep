package com.kinbo.boot2deep.config;


/**
 * 基于自定义注解注入的bean的创建者
 * @author songjunbao
 * @date 2024-08-26
 */
@Deprecated
public class MyAutowiredBeanBuilder implements AutowiredBeanBuilder<MyAutowired> {


    @Override
    public Object build(MyAutowired autowiredAnnotation, Class<?> beanType) {
        System.out.println("beanType:" +beanType.getSimpleName());
        MyAutoAwareBean myAutoAwareBean = new MyAutoAwareBean();
        myAutoAwareBean.setParam(autowiredAnnotation.value());
        return myAutoAwareBean;
    }
}
