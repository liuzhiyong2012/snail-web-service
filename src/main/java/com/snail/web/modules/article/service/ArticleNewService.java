package com.snail.web.modules.article.service;

import com.baomidou.mybatisplus.service.IService;
import com.snail.web.dto.BaseResponse;
import com.snail.web.dto.PageBaseResponse;
import com.snail.web.modules.article.dto.ArticleNew;

public interface ArticleNewService extends IService<ArticleNew> {
    BaseResponse insert(ArticleNew articleNew, String userId);
    PageBaseResponse page(ArticleNew articleNew, String userId);
    BaseResponse delete(ArticleNew articleNew, String userId);
    BaseResponse update(ArticleNew articleNew, String userId);

}
