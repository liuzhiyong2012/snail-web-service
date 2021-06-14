package com.snail.web.modules.crawler.spider.story;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Holinc
 */
@Gecco(matchUrl = {
		"http://www.weishangshijie.cn/news/",
		"http://www.weishangshijie.cn/duanzi/",
		"http://www.weishangshijie.cn/xueyuan/"},
		pipelines = "articlePipeline")
public class ArticleSpider implements HtmlBean {

	@Request
	private HttpRequest request;

	private String parentTypeCode;

	@HtmlField(cssPath = "body > div.menu_dh_1 > ul > li")
	private List<Item> subTypes;

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public String getParentTypeCode() {
		if (null != this.getRequest() && StringUtils.isNotBlank(this.getRequest().getUrl())) {
			URI uri;
			try {
				uri = new URI(this.getRequest().getUrl());
			} catch (URISyntaxException e) {
				return null;
			}
			return uri.getPath().replace("/", "");
		}
		return parentTypeCode;
	}

	public void setParentTypeCode(String parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}

	public List<Item> getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(List<Item> subTypes) {
		this.subTypes = subTypes;
	}

	public static class Item implements HtmlBean {

		@Href(click = true)
		@HtmlField(cssPath = "li a")
		private String url;

		@Text
		@HtmlField(cssPath = "li a")
		private String name;

		private String code;


		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}

}
