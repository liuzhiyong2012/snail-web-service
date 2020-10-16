package com.snail.web.common;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
/*    @Bean(name="dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }*/

    // 配置事物管理器
   /* @Bean(name="transactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }*/
}
