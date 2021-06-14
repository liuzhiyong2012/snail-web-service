package com.snail.web.modules.crawler.spider.news;

import com.geccocrawler.gecco.pipeline.Pipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Holinc
 */
public class NewsListPipeline implements Pipeline<NewsList> {

	private static final Logger log = LoggerFactory.getLogger(NewsListPipeline.class);

	@Override
	public void process(NewsList newsList) {
		//新闻列表处理
		log.info("newsListPipeline process......");
	}

}
