package com.zhang.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

/**
 * @Author: diexi
 * @Date: 2022/4/24 9:26
 * @ClassName: BeanFatroty
 */
@Configuration
public class BeanFactoryU implements ApplicationContextAware {



    /**
     * Spring应用上下文环境
     */
    public static ApplicationContext applicationContext;
    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        BeanFactoryU.applicationContext = applicationContext;
    }

    /**
     * 获取bean实例
     * @param name
     * @param <T>
     */
    public <T>T getBean(String name){
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取bean类型
     * @param name
     * @param <T>
     */
    public <T>T getType(String name){
        Assert.isNull(name,"未获取到实例名称");
        return (T) applicationContext.getType(name);
    }

    /**
     * 判断beanFactory中是否存在改bean
     */
    public boolean containsBean(String name){
        return applicationContext.containsBean(name);
    }

    /**
     * 判断beanFactory中的bean是Singleton单例还是多实例prototype
     */
    public boolean isSingleton(String name){
        return applicationContext.isSingleton(name);
    }

    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }
}
