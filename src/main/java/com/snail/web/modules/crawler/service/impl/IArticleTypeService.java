package com.snail.web.modules.crawler.service.impl;

import com.snail.web.modules.crawler.dto.entity.ArticleType;
import com.snail.web.modules.crawler.mapper.ArticleTypeMapper;
import com.snail.web.modules.crawler.service.ArticleTypeService;
import com.snail.web.modules.crawler.spider.story.ArticleSpider;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Holinc
 */
@Service
public class IArticleTypeService implements ArticleTypeService {
	@Autowired
	private ArticleTypeMapper articleTypeMapper;

	@Override
	public void saveArticleType(ArticleSpider articleSpider) {
		if (null == articleSpider || CollectionUtils.isEmpty(articleSpider.getSubTypes())) {
			return;
		}
		articleSpider.getSubTypes().forEach(item -> {
			Map<String, Object> queryMap = new HashMap<>();
			queryMap.put("code", item.getCode());
			List<ArticleType> articleTypeInDb = articleTypeMapper.selectByMap(queryMap);
			if (CollectionUtils.isNotEmpty(articleTypeInDb)) {
				//已存在，则更新
				ArticleType articleType = articleTypeInDb.get(0);
				articleType.setName(item.getName());
				articleTypeMapper.update(articleType, null);
			} else {
				//不存在，则新建
				queryMap.put("code", articleSpider.getParentTypeCode());
				List<ArticleType> parentArticleType = articleTypeMapper.selectByMap(queryMap);

				ArticleType articleType = new ArticleType();
				articleType.setCode(item.getCode());
				articleType.setName(item.getName());
				if (CollectionUtils.isNotEmpty(parentArticleType)) {
					articleType.setParentId(parentArticleType.get(0).getId());
				}
				articleTypeMapper.insert(articleType);
			}
		});
	}
}
