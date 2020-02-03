package com.paging.dao;

public interface PagingDao {
	
	String TOTAL_PAGE_SQL = " SELECT COUNT(*) FROM BOARD ";
	
	public int totalPage();

}
