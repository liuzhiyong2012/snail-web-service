package com.snail.web.modules.crawler.spider.news;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.snail.web.modules.crawler.service.NewsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Holinc
 */
public class NewsDetailPipeline implements Pipeline<NewsDetail> {

	private static final Logger log = LoggerFactory.getLogger(NewsDetailPipeline.class);


	@Autowired
	private NewsService newsService;

	@Override
	public void process(NewsDetail newsDetail) {
		//新闻详情处理
		log.info("newsDetailPipeline process......");

		this.parse(newsDetail);
		newsService.saveNewsDetail(newsDetail);
	}

	/**
	 * 解析
	 *
	 * @param newsDetail
	 */
	private void parse(NewsDetail newsDetail) {
		//解析文章来源和发布时间
		//sample
		//来源：微商世界网 日期：2021-06-12 21:54:36
		if (null != newsDetail && StringUtils.isNotBlank(newsDetail.getSourceText())) {
			String sourceText = newsDetail.getSourceText();
			sourceText = sourceText.replace("来源：", "").replace("日期：", "@@");
			String[] split = sourceText.split("@@");
			if (split.length == 2) {
				try {
					//文章来源
					newsDetail.setSource(split[0].trim());

					//发布日期
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					newsDetail.setPublishTime(LocalDateTime.parse(split[1], formatter));
				} catch (Exception e) {
					log.error("解析文章来源和发布时间失败");
					log.error(e.getMessage(), e);
				}
			}
		}


	}
}
