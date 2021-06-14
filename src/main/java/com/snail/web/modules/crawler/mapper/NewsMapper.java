package com.snail.web.modules.crawler.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.snail.web.modules.crawler.dto.entity.News;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Holinc
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {

}
