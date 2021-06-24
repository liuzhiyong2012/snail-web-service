package com.snail.web.modules.article.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.article.dto.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("articleMapper")
public interface ArticleMapper extends BaseMapper<Article> {
    List<Article> page(Article Article);

    void deleteRecord(Article Article);

    int count(Article Article);

    Article getArticle(Article article);

}
