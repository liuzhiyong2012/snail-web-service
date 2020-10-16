package com.snail.web.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureTest {
    @Bean
    public Object getNewObj() {
        System.out.println();
        return new Object();
    }
}
