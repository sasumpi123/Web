package com.mvc.biz;

import java.util.List;

import com.mvc.dao.MVCDao;
import com.mvc.dao.MVCDaoImpl;
import com.mvc.dto.MVCDto;

public class MVCBizImpl implements MVCBiz {
	MVCDao dao = new MVCDaoImpl();
	@Override
	public List<MVCDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public MVCDto selectOne(int myno) {
		// TODO Auto-generated method stub
		return dao.selectOne(myno);
	}

	@Override
	public int insert(MVCDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(MVCDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int myno) {
		// TODO Auto-generated method stub
		return dao.delete(myno);
	}

	@Override
	public boolean multiDelete(String[] myno) {
		// TODO Auto-generated method stub
		return dao.multiDelete(myno);
	}

}
