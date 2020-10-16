package com.snail.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author: guijin
 * @date: 2019/12/18
 * @remark: 容器工具类
 */
@Component
@Lazy(false)
public class SpringContextUtils implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext;

    @Override
    public void destroy() throws Exception {
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> classType){
        return (T)applicationContext.getBean(classType);
    }
}
