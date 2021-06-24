package com.snail.web.modules.crawler.spider.story;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.snail.web.constants.BaseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Holinc
 */
@PipelineName("articleTypePipeLine")
public class ArticleTypePipeLine implements Pipeline<ArticleType> {

	private static final Logger log = LoggerFactory.getLogger(ArticleTypePipeLine.class);


	@Override
	public void process(ArticleType bean) {
		log.info("222222222222222222222222222222222222......");
		String NumberStr = bean.getPage().replaceAll("条","");
		int pageNumber = Integer.parseInt(NumberStr) ;
		int totalMPage = Math.min(BaseConstant.SPIDER_MAX_PAGE,pageNumber);

		for(int i = 0 ;i < totalMPage;i++){
			HttpRequest request = bean.getRequest();
			HttpRequest currRequest = bean.getRequest();
			Map paraMap = new HashMap<String,String>();
			paraMap  =currRequest.getParameters();
			String firstTypeCode = (String)paraMap.get("firstTypeCode");
			String secondTypeCode = (String)paraMap.get("secondTypeCode");
			URI uri = null;
				try {
					uri  = new URI(currRequest.getUrl());
				} catch (URISyntaxException e) {

				}

			String path = uri.getPath();
			String host = uri.getHost();
			String nextUrl =uri.getScheme() + "://" + host + "/" + secondTypeCode + "_"+ totalMPage + "/";

			HttpRequest nextRequest = currRequest.subRequest(nextUrl);
			Map para = new HashMap<String,String>();
			para.put("fisrtTypeCode",firstTypeCode);
			para.put("secondTypeCode",secondTypeCode);

			nextRequest.setParameters(para);
			SchedulerContext.into(nextRequest);

		}

		/*String path = "";
		if (null != this.getRequest() && StringUtils.isNotBlank(this.getRequest().getUrl())) {
			URI uri;
			try {
				uri = new URI(this.getRequest().getUrl());
			} catch (URISyntaxException e) {
				return null;
			}
			path = uri.getPath().replace("/", "");

		}
		String firstTypeCode = ArticleConstant.IMFORMATION_CRAWLER_MAP.get(path);
		return firstTypeCode;*/

	}




}
