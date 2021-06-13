package com.snail.web.modules.crawler.spider.news;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.time.LocalDateTime;

@Gecco(matchUrl = "http://www.weishangshijie.cn/newsp/{page}.html",
		pipelines = "newsDetailPipeline", timeout = 1000)
public class NewsDetail implements HtmlBean {

	@Request
	private HttpRequest request;

	@RequestParameter(value = "page")
	private Integer page;

	/**
	 * 文章标题
	 */
	@Text
	@HtmlField(cssPath = ".content .font_content h1")
	private String title;

	/**
	 * 文章来源
	 */
	@Text
	@HtmlField(cssPath = ".content .foot_content_laiy")
	private String source;

	/**
	 * 发布日期
	 */
	private LocalDateTime pulishDateTime;

	/**
	 * 导语
	 */
	@Text
	@HtmlField(cssPath = ".daoyu")
	private String summary;

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public LocalDateTime getPulishDateTime() {
		return pulishDateTime;
	}

	public void setPulishDateTime(LocalDateTime pulishDateTime) {
		this.pulishDateTime = pulishDateTime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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
