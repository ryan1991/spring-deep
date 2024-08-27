package com.kinbo.boot2deep.config;

/**
 * @author songjunbao
 * @date 2024-08-27
 */
public abstract class BeanNameUtils {

    private final static String BEAN_NAME_SEPARATOR = "#";

    public static String buildBeanName(Class<?> type, String source, String param){

        return type.getName() + BEAN_NAME_SEPARATOR + source +BEAN_NAME_SEPARATOR+ param;
    }
}
