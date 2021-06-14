package com.snail.web.modules.crawler.spider;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import com.snail.web.modules.crawler.spider.news.NewsDetailPipeline;
import com.snail.web.modules.crawler.spider.news.NewsListPipeline;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Holinc
 */
@Configuration
public class CrawlerConfigure {

	@Bean(name = "newsListPipeline")
	public NewsListPipeline newsListPipeline() {
		return new NewsListPipeline();
	}

	@Bean(name = "newsDetailPipeline")
	public NewsDetailPipeline newsDetailPipeline() {
		return new NewsDetailPipeline();
	}

	@Bean
	public SpringGeccoEngine initGecco() {
		return new SpringGeccoEngine() {
			@Override
			public void init() {
				GeccoEngine.create()
						.pipelineFactory(springPipelineFactory)
						.classpath("com.snail.web.modules.crawler.spider.news")
						.seed("http://www.weishangshijie.cn/news/",
								"http://www.weishangshijie.cn/news_2/")
						.interval(30000)
						.debug(true)
						.loop(true)
						.start();
			}
		};
	}

}
