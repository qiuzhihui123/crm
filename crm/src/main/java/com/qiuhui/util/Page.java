package com.qiuhui.util;

import java.util.List;

public class Page<T> {
	private int pageNo;
	private int totalPage;
	private int pageSize = 3;
	private List<T> items;
	private int start;
	
	public Page(int pageNo, int totalCount) {
		this.totalPage = totalCount/pageSize;
		if(totalCount%pageSize != 0){
			totalPage++;
		}
		if(pageNo > totalPage){
			pageNo = totalPage;
		}
		if(pageNo < 1){
			pageNo = 1;
		}
		this.pageNo = pageNo;
		this.start = (pageNo-1) * pageSize;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	
}
