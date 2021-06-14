package com.snail.web.modules.crawler.spider.story;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.snail.web.modules.crawler.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Holinc
 */
public class ArticleDetailPipeline implements Pipeline<ArticleDetail> {

	private static final Logger log = LoggerFactory.getLogger(ArticleDetailPipeline.class);

	@Autowired
	private ArticleService articleService;

	@Override
	public void process(ArticleDetail bean) {
		//文章详情处理
		log.info("articleDetailPipeline process......");

		this.parse(bean);
		articleService.saveArticleDetail(bean);
	}

	/**
	 * 解析
	 *
	 * @param articleDetail
	 */
	private void parse(ArticleDetail articleDetail) {
		//解析文章来源和发布时间
		//sample
		//来源：微商世界网 日期：2021-06-12 21:54:36
		if (null != articleDetail && StringUtils.isNotBlank(articleDetail.getSourceText())) {
			String sourceText = articleDetail.getSourceText();
			sourceText = sourceText.replace("来源：", "").replace("日期：", "@@");
			String[] split = sourceText.split("@@");
			if (split.length == 2) {
				try {
					//文章来源
					articleDetail.setSource(split[0].trim());

					//发布日期
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					articleDetail.setPublishTime(LocalDateTime.parse(split[1], formatter));
				} catch (Exception e) {
					articleDetail.setSource(sourceText);
					log.error("解析文章来源和发布时间失败");
					log.error(e.getMessage(), e);
				}
			}
		}
	}
}
