package com.snail.web.modules.crawler.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.crawler.dto.entity.ArticleType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Holinc
 */
@Mapper
public interface ArticleTypeMapper extends BaseMapper<ArticleType> {
       int count(ArticleType articleType);

       List<ArticleType> page(ArticleType articleType);

       void deleteRecord(ArticleType articleType);
//       void insertRecord(ArticleType articleType);

}
