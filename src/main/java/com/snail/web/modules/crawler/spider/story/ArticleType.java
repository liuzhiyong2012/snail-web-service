package com.snail.web.modules.crawler.spider.story;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import com.snail.web.constants.BaseConstant;

/**
 * @author Holinc
 */
@Gecco(matchUrl = {BaseConstant.SPIDER_HOST +"/{subArticleType}/"},pipelines = "articleTypePipeLine")
public class ArticleType implements HtmlBean {

	@Request
	private HttpRequest request;

	@RequestParameter(value = "subArticleType")
	private String subArticleType;

	//#fisrtTypeCode={test1}&secondTypeCode={test2}
	@RequestParameter(value = "test1")
	private String test1;

	@RequestParameter(value = "test2")
	private String test2;

	@HtmlField(cssPath = "body > div.content .page > .a1:nth-child(1)")
    @Text
    private String page;



	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public String getSubArticleType() {
		return subArticleType;
	}

	public void setSubArticleType(String subArticleType) {
		this.subArticleType = subArticleType;
	}


	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}




}
