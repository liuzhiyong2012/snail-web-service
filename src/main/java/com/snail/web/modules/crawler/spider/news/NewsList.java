package com.snail.web.modules.crawler.spider.news;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * @author Holinc
 */
//@Gecco(matchUrl = {"http://www.weishangshijie.cn/news/", "http://www.weishangshijie.cn/news_{page}/"},
//		pipelines = "newsListPipelineAAA")
public class NewsList implements HtmlBean {

	@Request
	private HttpRequest request;

	@RequestParameter(value = "page")
	private Integer page;

	@HtmlField(cssPath = "body > div.content > div.nzlist_l > div.news_list > ul > li")
	private List<Item> newsList;

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

	public List<Item> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<Item> newsList) {
		this.newsList = newsList;
	}

	public static class Item implements HtmlBean {

		@Href(click = true)
		@HtmlField(cssPath = ".tit a")
		private String url;

		@Text
		@HtmlField(cssPath = ".tit a")
		private String title;

		@Text
		@HtmlField(cssPath = ".laiy")
		private String source;

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
