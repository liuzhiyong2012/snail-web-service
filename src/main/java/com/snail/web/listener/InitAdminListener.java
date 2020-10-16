package com.snail.web.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitAdminListener implements ApplicationListener<ContextRefreshedEvent> {


    //所有bean加载完成之后要做的逻辑
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("所有的bean加载完成！");
    }
}