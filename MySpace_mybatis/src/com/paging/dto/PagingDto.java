package com.paging.dto;

public class PagingDto {
	
	private int rows;
	private int page;
	private int startpage;
	private int endpage;
	private int pagescale;
	private int pagegroup;
	private int totalpage;
	private int to;
	private int from;
	
	public PagingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PagingDto(int rows, int page, int startpage, int endpage, int pagescale, int pagegroup, int totalpage,
			int to, int from) {
		super();
		this.rows = rows;
		this.page = page;
		this.startpage = startpage;
		this.endpage = endpage;
		this.pagescale = pagescale;
		this.pagegroup = pagegroup;
		this.totalpage = totalpage;
		this.to = to;
		this.from = from;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStartpage() {
		return startpage;
	}
	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	public int getPagescale() {
		return pagescale;
	}
	public void setPagescale(int pagescale) {
		this.pagescale = pagescale;
	}
	public int getPagegroup() {
		return pagegroup;
	}
	public void setPagegroup(int pagegroup) {
		this.pagegroup = pagegroup;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	
	
	
	

}
