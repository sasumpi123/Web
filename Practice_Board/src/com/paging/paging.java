package com.paging;

public class paging {
	private int startPage;
	private int endPage;
	private int totalPage;
	private int Page;
	private int nowPage;
	private boolean next;
	private boolean prev;
	private int rowPage = 10;
	private int colPage = 10;

	public paging() {
		//Page = (nowPage/rowPage)+1;
		startPage = ((Page/colPage)*10)+1;
		endPage = startPage + colPage - 1;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return Page;
	}

	public void setPage(int page) {
		this.Page = page;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public int getRowPage() {
		return rowPage;
	}

	public void setRowPage(int rowPage) {
		this.rowPage = rowPage;
	}

	public int getColPage() {
		return colPage;
	}

	public void setColPage(int colPage) {
		this.colPage = colPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	

}
