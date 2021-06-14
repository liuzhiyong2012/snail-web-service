package com.snail.web.modules.crawler.spider.news;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.snail.web.modules.crawler.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Holinc
 */
public class NewsDetailPipeline implements Pipeline<NewsDetail> {

	@Autowired
	private NewsService newsService;

	@Override
	public void process(NewsDetail newsDetail) {
		//新闻详情处理
		newsService.saveNewsDetail(newsDetail);
	}

}
