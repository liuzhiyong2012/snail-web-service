package com.snail.web.modules.crawler.spider.story;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

/**
 * @author Holinc
 */
@PipelineName("articleDetailPipeline")
public class ArticleDetailPipeline implements Pipeline<ArticleDetail> {

	@Override
	public void process(ArticleDetail bean) {
		System.out.println(JSON.toJSONString(bean));
	}

}
