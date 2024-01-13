package com.kinbo.boot2deep.config.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * 将被@FeignClient标注的接口，创建FactoryBean类定义，获取注解的参数动态设置属性
 *
 * @author songjunbao
 * @date 2024-01-11
 */
@Component
public class FeignClientBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathBeanDefinitionScanner scanner = getScanner(registry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(FeignClient.class));
        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents("com.kinbo.boot2deep.config.factory");
        for (BeanDefinition candidateComponent : candidateComponents){
            GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition(candidateComponent);
            genericBeanDefinition.setBeanClass(FeignClientFactoryBean.class);
            genericBeanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            AnnotationMetadata metadata = ((ScannedGenericBeanDefinition) candidateComponent).getMetadata();
            genericBeanDefinition.getPropertyValues().addPropertyValue("feignInterface", metadata.getClassName());
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(FeignClient.class.getName());
            for(Map.Entry<String, Object> e : annotationAttributes.entrySet()){
                //FeignClientFactoryBean的属性设值
                //注意 属性需要提供get set方法
                genericBeanDefinition.getPropertyValues().addPropertyValue(e.getKey(), e.getValue());
            }
            registry.registerBeanDefinition(metadata.getClassName(), genericBeanDefinition);
        }
    }

    private ClassPathBeanDefinitionScanner getScanner(BeanDefinitionRegistry registry) {
        return new ClassPathBeanDefinitionScanner(registry, false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isIndependent() && !beanDefinition.getMetadata().isAnnotation();
            }
        };
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
