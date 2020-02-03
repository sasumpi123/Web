package com.paging.biz;

import com.paging.dao.PagingDao;
import com.paging.dao.PagingDaoImpl;

public class PagingBizImpl implements PagingBiz {
	
	private PagingDao dao = new PagingDaoImpl();

	@Override
	public int totalPage(int rows) {
		int totalpage = (int)Math.ceil((double)dao.totalPage()/rows);
		return totalpage;
	}

}
