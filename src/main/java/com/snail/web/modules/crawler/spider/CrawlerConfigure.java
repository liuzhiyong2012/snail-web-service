package com.snail.web.modules.crawler.spider;

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

//	@Bean(name = "newsListPipeline")
////	public NewsListPipeline newsListPipeline() {
////		return new NewsListPipeline();
////	}
////
////	@Bean(name = "newsDetailPipeline")
////	public NewsDetailPipeline newsDetailPipeline() {
//		return new NewsDetailPipeline();
//	}

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
						.seed("http://www.weishangshijie.cn/news/")
						.seed("http://www.weishangshijie.cn/duanzi/")
						.seed("http://www.weishangshijie.cn/xueyuan/")
						.seed("http://www.weishangshijie.cn/xueyuan/")
						.thread(3)
						.interval(300000000)
 					    .debug(true)
						.loop(false)
					//	.stop()
						.start();

			}
		};
	}*/

}
