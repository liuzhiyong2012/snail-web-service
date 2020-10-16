package com.snail.web.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private CheckLoginAspect checkLoginAspect;
    @Autowired
    private CheckOutAspect checkOutAspect;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkLoginAspect).addPathPatterns("/**");
        registry.addInterceptor(checkOutAspect).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
