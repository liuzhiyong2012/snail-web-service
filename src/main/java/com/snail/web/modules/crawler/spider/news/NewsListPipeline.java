package com.snail.web.modules.crawler.spider.news;

import com.geccocrawler.gecco.pipeline.Pipeline;

/**
 * @author Holinc
 */
public class NewsListPipeline implements Pipeline<NewsList> {

	@Override
	public void process(NewsList newsList) {
		//新闻列表处理
		//do nothing
		System.out.println(newsList);
	}

}
