package com.kinbo.boot2deep.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author songjunbao
 * @date 2024-08-27
 */
public  class MyAutowiredBeanRegistrar extends AbstractAutowiredBeanRegistrar<MyAutoAwareBean, MyAutowired> {



    public MyAutowiredBeanRegistrar(DefaultListableBeanFactory beanFactory, MyAutowired myAutowired) {
        super(beanFactory, myAutowired);
    }

    @Override
    protected String buildBeanName() {
        return BeanNameUtils.buildBeanName(MyAutoAwareBean.class, ConfigCenterSource.LOCAL.name(), autowiredBeanAnnotation.value());
    }

    @Override
    protected MyAutoAwareBean build() {
        String beanName = buildBeanName();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MyAutoAwareBean.class);
        builder.addPropertyValue("param", autowiredBeanAnnotation.value());
        registerBeanDefinition(beanName, builder.getBeanDefinition());
        return (MyAutoAwareBean)beanFactory.getBean(beanName);
    }
}
