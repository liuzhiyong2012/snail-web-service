package com.snail.web.entity;


import java.io.Serializable;

public class PageBaseResponse<T> extends BaseResponse<T> implements Serializable {
	private Integer pageNumber;
	private Integer pageSize;
	private Integer total;
	private Integer totalPage;
	private Integer start;
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public PageBaseResponse() {
		this.setCode(1000);
		this.total = 0;
		this.totalPage = 0;
	}

	public void setPageNumber(Integer page) {
		this.pageNumber = page;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotal() {
		return total;
	}

	public Integer getStart(){
		return pageNumber==null?0:(pageNumber-1)*pageSize;
	}

}
