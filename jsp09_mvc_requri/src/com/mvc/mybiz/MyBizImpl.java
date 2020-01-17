package com.mvc.mybiz;

import java.util.List;

import com.mvc.mydao.MyDao;
import com.mvc.mydao.MyDaoImpl;
import com.mvc.mydto.MyDto;

public class MyBizImpl implements MyBiz {

	MyDao dao = new MyDaoImpl();
	@Override
	public List<MyDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public MyDto selectOne(int myno) {
		// TODO Auto-generated method stub
		return dao.selectOne(myno);
	}

	@Override
	public int insert(MyDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(MyDto dto) {
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
