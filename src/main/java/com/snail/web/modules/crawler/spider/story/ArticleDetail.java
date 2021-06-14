package com.snail.web.modules.crawler.spider.story;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.time.LocalDateTime;

@Gecco(matchUrl = "http://www.weishangshijie.cn/{subType}/{page}.html",
		pipelines = "articleDetailPipeline", timeout = 1000)
public class ArticleDetail implements HtmlBean {

	@Request
	private HttpRequest request;

	@RequestParameter(value = "subType")
	private String subType;

	@RequestParameter(value = "page")
	private Integer page;

	/**
	 * 文章标题
	 */
	@Text
	@HtmlField(cssPath = ".content .font_content h1")
	private String title;

	/**
	 * 文章来源及时间
	 */
	@Text
	@HtmlField(cssPath = ".content .foot_content_laiy")
	private String sourceText;

	/**
	 * 文章来源
	 */
	private String source;

	/**
	 * 发布日期
	 */
	private LocalDateTime publishTime;

	/**
	 * 导语
	 */
	@Text
	@HtmlField(cssPath = ".daoyu")
	private String summary;


	@HtmlField(cssPath = ".content > div.nzlist_l > div.news_list > ul > li:nth-child(1) > div.h_l")
	private String imageUrl;

	/**
	 * 正文
	 */
	@Text(own = false)
	@HtmlField(cssPath = "#font_content_n")
	private String content;

	/**
	 * 正文（带格式）
	 */
	@HtmlField(cssPath = "#font_content_n")
	private String rawContent;


	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}


	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSourceText() {
		return sourceText;
	}

	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public LocalDateTime getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(LocalDateTime publishTime) {
		this.publishTime = publishTime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRawContent() {
		return rawContent;
	}

	public void setRawContent(String rawContent) {
		this.rawContent = rawContent;
	}

}
