package com.snail.web.modules.crawler.service.impl;

import com.snail.web.modules.crawler.dto.entity.News;
import com.snail.web.modules.crawler.mapper.NewsMapper;
import com.snail.web.modules.crawler.service.NewsService;
import com.snail.web.modules.crawler.spider.news.NewsDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Holinc
 */
@Service
public class INewsService implements NewsService {
	@Autowired
	private NewsMapper newsMapper;

	@Override
	public void saveNewsDetail(NewsDetail newsDetail) {
		if (null == newsDetail) {
			return;
		}
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("title", newsDetail.getTitle());
		List<News> newsInDb = newsMapper.selectByMap(queryMap);
		if (newsInDb.isEmpty()) {
			//已存在，跳过
			return;
		}
		News news = new News();
		BeanUtils.copyProperties(newsDetail, news);
		newsMapper.insert(news);
	}

	@Override
	public void saveNewsDetail(List<NewsDetail> newsDetailList) {

	}
}
