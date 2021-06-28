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
			String secondNextUrl = (String)paraMap.get("secondNextUrl");

			URI uri = null;
				try {
					uri  = new URI(currRequest.getUrl());
				} catch (URISyntaxException e) {

				}

			String path = uri.getPath();
			String host = uri.getHost();
			String nextUrl =uri.getScheme() + "://" + host + "/" + secondNextUrl + "_"+ (i + 1) + "/";

			HttpRequest nextRequest = currRequest.subRequest(nextUrl);
			Map para = new HashMap<String,String>();
			para.put("fisrtTypeCode",firstTypeCode);
			para.put("secondTypeCode",secondTypeCode);
			log.info("222222222:nextUrl:" + nextUrl);
			nextRequest.setParameters(para);
			SchedulerContext.into(nextRequest);

		}



	}




}
