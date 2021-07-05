package com.snail.web.modules.article.service;

import com.baomidou.mybatisplus.service.IService;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.article.dto.entity.Article;
import com.snail.web.modules.article.dto.entity.ArticleType;

public interface ArticleService extends IService<Article> {
    BaseResponse insert(Article article, String userId);
    PageBaseResponse page(Article article, String userId);
    BaseResponse delete(Article article, String userId);
    BaseResponse update(Article article, String userId);

    BaseResponse getNewlatestArticle(ArticleType articleType, String userId);

}
