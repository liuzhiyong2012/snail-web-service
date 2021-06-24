package com.snail.web.modules.crawler.service;

import com.snail.web.modules.crawler.spider.story.ArticleDetail;
import com.snail.web.modules.crawler.spider.story.ArticleSpider;

public interface SpiderService {
    void saveArticleType(ArticleSpider articleSpider);
    void saveArticleDetail(ArticleDetail articleDetail);

}
