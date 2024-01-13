package com.kinbo.boot2deep.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.core.annotation.AnnotationUtils.getAnnotation;

/**
 * @author songjunbao
 * @date 2023-12-07
 */
public class MyAutowiredAnnotationProcessor implements InstantiationAwareBeanPostProcessor {
    private final static Logger LOGGER = LoggerFactory.getLogger(MyAutowiredAnnotationProcessor.class);





    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        InjectionMetadata metadata = new InjectionMetadata(bean.getClass(), findFieldAutowiredMetadata(bean.getClass()));

        try {
            metadata.inject(bean, beanName, pvs);
        }
        catch (BeanCreationException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new BeanCreationException(beanName, "Injection of autowired dependencies failed", ex);
        }
        return pvs;

    }

    private List<InjectionMetadata.InjectedElement> findFieldAutowiredMetadata(final Class<?> beanClass) {
        final List<InjectionMetadata.InjectedElement> elements = new LinkedList<>();
        ReflectionUtils.doWithFields(beanClass, field -> {
            MyAutowired autowired = getAnnotation(field, MyAutowired.class);
            if (autowired != null) {
                if (Modifier.isStatic(field.getModifiers())) {
                    LOGGER.warn("@{} annotation is not supported on static fields: {}", MyAutowired.class, field);
                    return;
                }
                elements.add(new InjectionMetadata.InjectedElement(field, null){
                    @Override
                    protected void inject(Object target, String requestingBeanName, PropertyValues pvs) throws Throwable {
                        String value = autowired.value();
                        MyAutoAwareBean myAutoAwareBean = new MyAutoAwareBean();
                        myAutoAwareBean.setParam(value);
                        ReflectionUtils.makeAccessible(field);
                        field.set(target, myAutoAwareBean);
                    }
                });
            }
        });
        return elements;
    }




}
