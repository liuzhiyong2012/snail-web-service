package com.snail.web.modules.crawler.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.snail.web.modules.crawler.dto.entity.ArticleType;
import com.snail.web.modules.crawler.mapper.ArticleTypeMapper;
import com.snail.web.modules.crawler.service.ArticleTypeService;
import com.snail.web.modules.crawler.spider.story.ArticleSpider;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			ArticleType queryParam = new ArticleType();
			queryParam.setCode(item.getCode());
			ArticleType articleTypeInDb = articleTypeMapper.selectOne(queryParam);
			if (null != articleTypeInDb) {
				//已存在，则更新
				articleTypeInDb.setName(item.getName());
				EntityWrapper<ArticleType> wrapper = new EntityWrapper<>();
				wrapper.eq("id", articleTypeInDb.getId());
				articleTypeMapper.update(articleTypeInDb, wrapper);
			} else {
				//不存在，则新建
				ArticleType parentQueryParam = new ArticleType();
				parentQueryParam.setCode(articleSpider.getParentTypeCode());
				ArticleType parentArticleType = articleTypeMapper.selectOne(parentQueryParam);

				//判断父类别有没有，没有则新建
				if (null == parentArticleType) {
					parentArticleType = new ArticleType();
					parentArticleType.setCode(articleSpider.getParentTypeCode());
					ArticleSpider.ParentItem parentType = articleSpider.getParentType();
					if (null != parentType) {
						parentArticleType.setName(parentType.getName());
					} else {
						parentArticleType.setName(articleSpider.getParentTypeCode());
					}

					articleTypeMapper.insert(parentArticleType);
				}

				ArticleType articleType = new ArticleType();
				articleType.setCode(item.getCode());
				articleType.setName(item.getName());
				articleType.setParentId(parentArticleType.getId());
				articleTypeMapper.insert(articleType);
			}
		});
	}
}
