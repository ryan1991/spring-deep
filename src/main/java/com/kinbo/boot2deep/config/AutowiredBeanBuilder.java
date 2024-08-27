package com.kinbo.boot2deep.config;

/**
 * 基于自定义注解注入的bean的创建者
 * @author songjunbao
 * @date 2024-08-26
 */
public interface AutowiredBeanBuilder<A> {


    /**
     *
     * @param autowiredAnnotation
     * @param beanType
     * @return
     */
    Object build(A autowiredAnnotation, Class<?> beanType);

}
