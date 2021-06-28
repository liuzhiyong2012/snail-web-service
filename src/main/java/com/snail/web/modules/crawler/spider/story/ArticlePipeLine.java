package com.snail.web.modules.crawler.spider.story;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.snail.web.modules.article.service.ArticleTypeService;
import com.snail.web.modules.crawler.service.SpiderService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Holinc
 */
@PipelineName("articlePipeLine")
public class ArticlePipeLine implements Pipeline<ArticleSpider> {

	private static final Logger log = LoggerFactory.getLogger(ArticlePipeLine.class);

	@Autowired
	private ArticleTypeService articleTypeService;

	@Autowired
	private SpiderService spiderService;

	@Override
	public void process(ArticleSpider bean) {

		this.parse(bean);
		//articleTypeService.saveArticleType(bean);
		String firstTypeCode = bean.getFirstTypeCode();



		spiderService.saveArticleType(bean);
//}


		//跳转每一个二级分类页面
   	      for(ArticleSpider.Item item:bean.getSubTypes()){
			String nextUrl  = item.getUrl();
			//@Waring:对于贴子一类的东西需要特殊处理
			String secondTypeCode = firstTypeCode.equals("shop")?item.getCode(): bean.getSecondTypeCode();
			HttpRequest currRequest = bean.getRequest();
            String secondNextUrl = item.getCode();
			HttpRequest nextRequest = currRequest.subRequest(nextUrl);
			Map para = new HashMap<String,String>();
			para.put("firstTypeCode",firstTypeCode);
			para.put("secondTypeCode",secondTypeCode);
			  para.put("secondNextUrl",secondNextUrl);
			nextRequest.setParameters(para);
			log.info("111111111:nextUrl:" + nextUrl);
			SchedulerContext.into(nextRequest);
		}

	}

	private void parse(ArticleSpider bean) {
		if (null != bean && CollectionUtils.isNotEmpty(bean.getSubTypes())) {
			//解析二级类别
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
