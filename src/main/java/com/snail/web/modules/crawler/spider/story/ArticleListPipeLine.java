package com.snail.web.modules.crawler.spider.story;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Holinc
 */
@PipelineName("articleListPipeLine")
public class ArticleListPipeLine implements Pipeline<ArticleList> {

	private static final Logger log = LoggerFactory.getLogger(ArticleDetailPipeLine.class);

	/*@Autowired
	private ArticleService articleService;*/

	@Override
	public void process(ArticleList bean) {
		//log.info("333333333333333333......");

		List<ArticleList.Item> arleList = bean.getArticleList();
		for(ArticleList.Item item:arleList){

			HttpRequest currRequest = bean.getRequest();

			Map paraMap = new HashMap<String,String>();
			paraMap  =currRequest.getParameters();
			String firstTypeCode = (String)paraMap.get("firstTypeCode");
			String secondTypeCode = (String)paraMap.get("secondTypeCode");



			String nextUrl = "";
			nextUrl = item.getUrl1();
			if(null == nextUrl||"".equals(nextUrl)){
				nextUrl = item.getUrl();
			}


			HttpRequest nextRequest = currRequest.subRequest(nextUrl);
			Map para = new HashMap<String,String>();
			para.put("fisrtTypeCode",firstTypeCode);
			para.put("secondTypeCode",secondTypeCode);
			para.put("imageUrl",item.getImageUrl());
			log.info("333333333:nextUrl:" + nextUrl);
			nextRequest.setParameters(para);
			SchedulerContext.into(nextRequest);
		}


	}
}
