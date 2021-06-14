package com.snail.web.modules.crawler.service;

import com.snail.web.modules.crawler.spider.news.NewsDetail;
import com.snail.web.modules.crawler.spider.story.ArticleDetail;

import java.util.List;

/**
 * @author Holinc
 */
public interface ArticleService {
	void saveArticleDetail(ArticleDetail articleDetail);

	void saveArticleDetail(List<ArticleDetail> articleDetails);
}
