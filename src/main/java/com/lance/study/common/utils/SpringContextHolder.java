package com.lance.study.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    /**
     * 实现  ApplicationContextAware接口
     * @param applicationContext
     * @throws BeansException
     *
     * Spring容器会检测容器中的所有Bean，如果发现某个Bean实现了ApplicationContextAware接口，Spring容器会在创建该Bean之后，
     * 自动调用该Bean的setApplicationContextAware()方法，
     * 调用该方法时，会将容器本身作为参数传给该方法——
     * 该方法中的实现部分将Spring传入的参数（容器本身）赋给该类对象的applicationContext实例变量，
     * 因此接下来可以通过该applicationContext实例变量来访问容器本身。
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    public static <T> T getBean(String beanName) {
        assertContextInjected();
        return (T)applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> type) {
        assertContextInjected();
        return applicationContext.getBean(type);
    }



    private static void assertContextInjected(){
        if(null == applicationContext){
            throw new RuntimeException("applicationContext is null");
        }
    }
}
