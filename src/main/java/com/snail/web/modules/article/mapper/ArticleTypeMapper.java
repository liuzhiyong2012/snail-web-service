package com.snail.web.modules.article.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.article.dto.entity.ArticleType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Holinc
 */
@Mapper
@Component("articleTypeMapper")
public interface ArticleTypeMapper extends BaseMapper<ArticleType> {
       int count(ArticleType articleType);

       List<ArticleType> page(ArticleType articleType);

       void deleteRecord(ArticleType articleType);

       ArticleType getArticleType(ArticleType articleType);
//       void insertRecord(ArticleType articleType);

}
