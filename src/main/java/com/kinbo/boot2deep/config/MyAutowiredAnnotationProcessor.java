package com.kinbo.boot2deep.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.core.annotation.AnnotationUtils.getAnnotation;

/**
 * @author songjunbao
 * @date 2023-12-07
 */
public class MyAutowiredAnnotationProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware, AutowiredBeanBuilder<MyAutowired> {
    private final static Logger LOGGER = LoggerFactory.getLogger(MyAutowiredAnnotationProcessor.class);

    private DefaultListableBeanFactory beanFactory;

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        InjectionMetadata metadata = new InjectionMetadata(bean.getClass(), findAutowiredMetadata(bean.getClass()));
        try {
            metadata.inject(bean, beanName, pvs);
        } catch (BeanCreationException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new BeanCreationException(beanName, "Injection of autowired dependencies failed", ex);
        }
        return pvs;
    }

    private List<InjectionMetadata.InjectedElement> findAutowiredMetadata(final Class<?> beanClass){
        final List<InjectionMetadata.InjectedElement> elements = new LinkedList<>();
        elements.addAll(findFieldAutowiredMetadata(beanClass));
        elements.addAll(findMethodAutowiredMetadata(beanClass));
        return elements;
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
                elements.add(new MyAutowiredFieldInjectedElement(field, autowired, this));

//                elements.add(new InjectionMetadata.InjectedElement(field, null){
//                    @Override
//                    protected void inject(Object target, String requestingBeanName, PropertyValues pvs) throws Throwable {
//                        String value = autowired.value();
//                        MyAutoAwareBean myAutoAwareBean = new MyAutoAwareBean();
//                        myAutoAwareBean.setParam(value);
//                        ReflectionUtils.makeAccessible(field);
//                        field.set(target, myAutoAwareBean);
//                    }
//                });
            }
        });
        return elements;
    }

    private List<InjectionMetadata.InjectedElement> findMethodAutowiredMetadata(final Class<?> beanClass) {
        final List<InjectionMetadata.InjectedElement> elements = new LinkedList<>();
        ReflectionUtils.doWithLocalMethods(beanClass, method -> {
            Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
            if (!BridgeMethodResolver.isVisibilityBridgeMethodPair(method, bridgedMethod)) {
                return;
            }

            MyAutowired autowired = getAnnotation(method, MyAutowired.class);
            if (autowired != null && method.equals(ClassUtils.getMostSpecificMethod(method, beanClass))) {
                if (Modifier.isStatic(method.getModifiers())) {
                    LOGGER.warn("@{} annotation is not supported on static method: {}", MyAutowired.class, method);
                    return;
                }

                if (method.getParameterCount() == 0){
                    LOGGER.warn("@{} Autowired annotation should only be used on methods with parameters: : {}", MyAutowired.class, method);
                    return;
                }
                PropertyDescriptor pd = BeanUtils.findPropertyForMethod(bridgedMethod, beanClass);
                elements.add(new MyAutowiredMethodInjectedElement(method, pd,  autowired, this));
            }
        });
        return elements;
    }




//    @Override
//    public Object build(Object autowiredAnnotation, Class beanType) {
//        MyAutowiredBeanRegistrar myRegistrar = new MyAutowiredBeanRegistrar(beanFactory);
//        MyAutoAwareBean myAutoAwareBean = myRegistrar.register();
//        return myAutoAwareBean;
//    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object build(MyAutowired autowiredAnnotation, Class<?> beanType) {
        MyAutowiredBeanRegistrar myRegistrar = new MyAutowiredBeanRegistrar(beanFactory, autowiredAnnotation);
        MyAutoAwareBean myAutoAwareBean = myRegistrar.register();
        return myAutoAwareBean;
    }
}
