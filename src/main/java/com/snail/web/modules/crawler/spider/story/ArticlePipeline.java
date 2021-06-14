package com.snail.web.modules.crawler.spider.story;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.snail.web.modules.crawler.service.ArticleTypeService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Holinc
 */
@PipelineName("articlePipeline")
public class ArticlePipeline implements Pipeline<ArticleSpider> {

	private static final Logger log = LoggerFactory.getLogger(ArticlePipeline.class);

	@Autowired
	private ArticleTypeService articleTypeService;

	@Override
	public void process(ArticleSpider bean) {
		log.info("articlePipeline process......");
		this.parse(bean);
		articleTypeService.saveArticleType(bean);
	}

	private void parse(ArticleSpider bean) {
		if (null != bean && CollectionUtils.isNotEmpty(bean.getSubTypes())) {
			//解析二级类别
			//sample
			//http://www.weishangshijie.cn/wkeyuan/
			bean.getSubTypes().forEach(item -> {
				URI uri;
				try {
					uri = new URI(item.getUrl());
					item.setCode(uri.getPath().replace("/", ""));
				} catch (URISyntaxException e) {
					item.setCode(null);
				}
			});
		}
	}


}
