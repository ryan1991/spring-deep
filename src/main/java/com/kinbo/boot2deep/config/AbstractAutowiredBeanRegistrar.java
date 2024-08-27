package com.kinbo.boot2deep.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author songjunbao
 * @date 2024-08-27
 */
public abstract class AbstractAutowiredBeanRegistrar<T, A> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    protected DefaultListableBeanFactory beanFactory;
    private T autowiredBean;
    protected A autowiredBeanAnnotation;

    public AbstractAutowiredBeanRegistrar(DefaultListableBeanFactory beanFactory, A autowiredBeanAnnotation) {
        this.beanFactory = beanFactory;
        this.autowiredBeanAnnotation = autowiredBeanAnnotation;
    }

    protected void registerBeanDefinition(String beanName, BeanDefinition beanDefinition){
        beanDefinition.setPrimary(true);
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }

    protected void registerSingleton(String beanName, Object bean){
        beanFactory.registerSingleton(beanName, bean);
    }

    public T register(){
        String beanName = buildBeanName();
        logger.info("AutowiredBean buildBeanName:{}", beanName);
        if (beanFactory.containsBean(beanName)){
            logger.info("AutowiredBean已存在,直接返回:{}", beanName);
            autowiredBean = (T) beanFactory.getBean(beanName);

        }else {
            logger.info("AutowiredBean不存在,即刻创建:{}", beanName);
            autowiredBean = build();
//            registerSingleton(beanName, autowiredBean);
        }
        return autowiredBean;
    }

    protected abstract String buildBeanName();

    protected abstract T build();



}
