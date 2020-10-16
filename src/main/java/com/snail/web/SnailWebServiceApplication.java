package com.snail.web;

import com.baomidou.mybatisplus.annotations.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.snail.web.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class SnailWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnailWebServiceApplication.class, args);
	}

	@Bean
	public Object testBean(PlatformTransactionManager platformTransactionManager){
		System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
		return new Object();
	}

	// 其中 dataSource 框架会自动为我们注入
//	@Bean
//	public PlatformTransactionManager txManager(DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//	}



}
