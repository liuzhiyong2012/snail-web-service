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
		log.info("11111111111111111111111111......");
		this.parse(bean);
		//articleTypeService.saveArticleType(bean);
		spiderService.saveArticleType(bean);

		//跳转每一个二级分类页面

   	      for(ArticleSpider.Item item:bean.getSubTypes()){
		      String nextUrl  = item.getUrl();
			  HttpRequest currRequest = bean.getRequest();
			  nextUrl = nextUrl + "#fisrtTypeCode=" + bean.getFirstTypeCode() + "&secondTypeCode=" + bean.getSecondTypeCode();
			  HttpRequest nextRequest = currRequest.subRequest(nextUrl);
			  Map para = new HashMap<String,String>();
			  para.put("firstTypeCode",bean.getFirstTypeCode());
			  para.put("secondTypeCode",bean.getSecondTypeCode());

			  nextRequest.setParameters(para);
			  SchedulerContext.into(nextRequest);
		  }


		//下一页继续抓取
//		int currPage = 1;
//		int nextPage = currPage + 1;
//		int totalPage = 10;//articleList.getTotalPage();
//		if (nextPage <= totalPage) {
//			String nextUrl = "";
//			String currUrl = currRequest.getUrl();
//			if (currUrl.indexOf("page=") != -1) {
//				nextUrl = StringUtils.replaceOnce(currUrl, "page=" + currPage, "page=" + nextPage);
//			} else {
//				nextUrl = currUrl + "&" + "page=" + nextPage;
//			}
//			SchedulerContext.into(currRequest.subRequest(nextUrl));
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
