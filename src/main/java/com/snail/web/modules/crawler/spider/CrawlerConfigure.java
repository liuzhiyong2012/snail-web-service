package com.snail.web.modules.crawler.spider;

import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import com.snail.web.modules.crawler.spider.story.ArticleDetailPipeline;
import com.snail.web.modules.crawler.spider.story.ArticlePipeline;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Holinc
 */
@Configuration
public class CrawlerConfigure {

//	@Bean(name = "newsListPipeline")
////	public NewsListPipeline newsListPipeline() {
////		return new NewsListPipeline();
////	}
////
////	@Bean(name = "newsDetailPipeline")
////	public NewsDetailPipeline newsDetailPipeline() {
//		return new NewsDetailPipeline();
//	}

	@Bean(name = "articlePipeline")
	public ArticlePipeline articlePipeline() {
		return new ArticlePipeline();
	}

	@Bean(name = "articleDetailPipeline")
	public ArticleDetailPipeline articleDetailPipeline() {
		return new ArticleDetailPipeline();
	}


	@Bean
	public SpringGeccoEngine initGecco() {
		return new SpringGeccoEngine() {
			@Override
			public void init() {
//				GeccoEngine.create()
//						.pipelineFactory(springPipelineFactory)
//						.classpath("com.snail.web.modules.crawler.spider")
//						.seed("http://www.weishangshijie.cn/news/")
//						.seed("http://www.weishangshijie.cn/duanzi/")
//						.seed("http://www.weishangshijie.cn/xueyuan/")
//						.thread(3)
//						.interval(300000000)
////						.debug(true)
//						.loop(false)
//						.start();
			}
		};
	}

}
