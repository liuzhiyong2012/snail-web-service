package com.snail.web.modules.article.service;

import com.baomidou.mybatisplus.service.IService;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.article.dto.entity.ArticleType;
import com.snail.web.modules.crawler.spider.story.ArticleSpider;

/**
 * @author Holinc
 */
public interface ArticleTypeService extends IService<ArticleType> {
	void saveArticleType(ArticleSpider articleSpider);
	PageBaseResponse page(ArticleType ArticleType, String userId);
	BaseResponse insert(ArticleType ArticleType, String userId);
	BaseResponse delete(ArticleType ArticleType, String userId);
	BaseResponse update(ArticleType articleType, String userId);

}
