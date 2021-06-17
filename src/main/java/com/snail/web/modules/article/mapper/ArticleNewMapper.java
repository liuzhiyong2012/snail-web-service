package com.snail.web.modules.article.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.article.dto.ArticleNew;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleNewMapper extends BaseMapper<ArticleNew> {
    List<ArticleNew> page(ArticleNew articleNew);

    void deleteRecord(ArticleNew articleNew);

    int count(ArticleNew articleNew);

}
