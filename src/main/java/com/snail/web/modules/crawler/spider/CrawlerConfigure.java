package com.snail.web.modules.crawler.spider;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;
import com.snail.web.modules.crawler.spider.story.ArticleDetailPipeLine;
import com.snail.web.modules.crawler.spider.story.ArticleListPipeLine;
import com.snail.web.modules.crawler.spider.story.ArticlePipeLine;
import com.snail.web.modules.crawler.spider.story.ArticleTypePipeLine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Holinc
 */
@Configuration
public class CrawlerConfigure {


	@Bean(name = "articlePipeLine")
	public ArticlePipeLine articlePipeLine() {
		return new ArticlePipeLine();
	}

	@Bean(name = "articleDetailPipeLine")
	public ArticleDetailPipeLine articleDetailPipeLine() {
		return new ArticleDetailPipeLine();
	}

	@Bean(name = "articleTypePipeLine")
	public ArticleTypePipeLine articleTypePipeLine() {
		return new ArticleTypePipeLine();
	}


	@Bean(name = "articleListPipeLine")
	public ArticleListPipeLine articleListPipeLine() {
		return new ArticleListPipeLine();
	}


	/*@Bean
	public SpringGeccoEngine initGecco() {
		return new SpringGeccoEngine() {
			@Override
			public void init() {
				GeccoEngine.create()
						.pipelineFactory(springPipelineFactory)
						.classpath("com.snail.web.modules.crawler.spider")
						*//*.seed("http://www.weishangshijie.cn/news/")
						.seed("http://www.weishangshijie.cn/duanzi/")
						.seed("http://www.weishangshijie.cn/xueyuan/")
						.seed("http://www.weishangshijie.cn/xueyuan/")*//*
						.seed("http://weishangshijie.204m.com/index/")
						.thread(1)
						.interval(1000)
 					    .debug(false)
						.loop(false)
					//	.stop()
						.start();

			}
		};
	}*/

}
