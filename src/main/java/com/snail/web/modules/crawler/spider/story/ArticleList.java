package com.snail.web.modules.crawler.spider.story;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import com.snail.web.constants.BaseConstant;

import java.util.List;

/**
 * @author Holinc
 */
@Gecco(matchUrl = {
		BaseConstant.SPIDER_HOST +"/{subArticleType}_{page}/"},pipelines = "articleListPipeLine", timeout = 1000)
public class ArticleList implements HtmlBean {

	@Request
	private HttpRequest request;

	@RequestParameter(value = "subArticleType")
	private String subArticleType;

	@RequestParameter(value = "page")
	private Integer page;

	@HtmlField(cssPath = "body > div.content > div.nzlist_l > div.news_list > ul > li")
	private List<Item> articleList;

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

	public List<Item> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Item> articleList) {
		this.articleList = articleList;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}


	public static class Item implements HtmlBean {

		@Href(click = false)
		@HtmlField(cssPath = ".tit a")
		private String url;

		@Text
		@HtmlField(cssPath = ".tit a")
		private String title;

		@Text
		@HtmlField(cssPath = ".laiy")
		private String source;

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		@Image
		@HtmlField(cssPath = ".h_l >a >img")
		private String imageUrl;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
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
	}

}
