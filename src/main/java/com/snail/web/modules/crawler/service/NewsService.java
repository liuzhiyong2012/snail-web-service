package com.snail.web.modules.crawler.service;

import com.snail.web.modules.crawler.spider.news.NewsDetail;

import java.util.List;

public interface NewsService {
	void saveNewsDetail(NewsDetail newsDetail);

	void saveNewsDetail(List<NewsDetail> newsDetailList);
}
