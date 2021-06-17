package com.snail.web.modules.crawler.service.impl;

import com.snail.web.modules.crawler.service.ArticleService;
import com.snail.web.modules.crawler.spider.story.ArticleDetail;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Holinc
 */
@Service
public class IArticleService implements ArticleService {

	/*@Autowired
	private ArticleMapper articleMapper;*/

	@Override
	public void saveArticleDetail(ArticleDetail articleDetail) {
	/*	if (null == articleDetail) {
			return;
		}
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("title", articleDetail.getTitle());
		List<News> newsInDb = articleMapper.selectByMap(queryMap);
		if (CollectionUtils.isNotEmpty(newsInDb)) {
			//已存在，跳过
			return;
		}
		News news = new News();
		BeanUtils.copyProperties(articleDetail, news);
		articleMapper.insert(news);*/
	}

	@Override
	public void saveArticleDetail(List<ArticleDetail> articleDetails) {

	}
}
