package com.snail.web.modules.crawler.spider.story;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.snail.web.modules.crawler.service.SpiderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

/**
 * @author Holinc
 */
@PipelineName("articleDetailPipeLine")
public class ArticleDetailPipeLine implements Pipeline<ArticleDetail> {

	private static final Logger log = LoggerFactory.getLogger(ArticleDetailPipeLine.class);

	@Autowired
	private SpiderService spiderService;

	@Override
	public void process(ArticleDetail bean) {
		//文章详情处理
		log.info("44444444444444444444444444process......");

		this.parse(bean);
		spiderService.saveArticleDetail(bean);
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

			HttpRequest  request = articleDetail.getRequest();
			String firstTypeCode = request.getParameter("firstTypeCode");
			String secondTypeCode = request.getParameter("secondTypeCode");
			String imageUrl = request.getParameter("imageUrl");

			articleDetail.setFirstTypeCode(firstTypeCode);
			articleDetail.setSecondTypeCode(secondTypeCode);
			articleDetail.setImageUrl(imageUrl);
			if (split.length == 2) {
				try {
					//文章来源
					articleDetail.setSource(split[0].trim());

					//发布日期
					//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					/*String value=20150706;
					df.parse(value)*/
					articleDetail.setPublishTime(df.parse(split[1]));

				} catch (Exception e) {
					articleDetail.setSource(sourceText);
					log.error("解析文章来源和发布时间失败");
					log.error(e.getMessage(), e);
				}
			}
		}
	}
}
