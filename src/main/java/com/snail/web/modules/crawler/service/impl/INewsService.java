package com.snail.web.modules.crawler.service.impl;

import com.snail.web.modules.crawler.dto.entity.News;
import com.snail.web.modules.crawler.mapper.NewsMapper;
import com.snail.web.modules.crawler.service.NewsService;
import com.snail.web.modules.crawler.spider.news.NewsDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class INewsService implements NewsService {
	@Autowired
	private NewsMapper newsMapper;

	@Override
	public void saveNewsDetail(NewsDetail newsDetail) {
		News news = new News();
		BeanUtils.copyProperties(newsDetail, news);
		newsMapper.insert(news);
	}

	@Override
	public void saveNewsDetail(List<NewsDetail> newsDetailList) {

	}
}
