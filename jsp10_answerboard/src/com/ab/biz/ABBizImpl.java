package com.ab.biz;

import java.util.List;

import com.ab.dao.ABDao;
import com.ab.dao.ABDaoImpl;
import com.ab.dto.ABDto;

public class ABBizImpl implements ABBiz {

	ABDao dao = new ABDaoImpl();

	@Override
	public List<ABDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public ABDto selectOne(int boardno) {
		// TODO Auto-generated method stub
		return dao.selectOne(boardno);
	}

	@Override
	public int update(ABDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int insert(ABDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int insertAnswer(ABDto dto, int boardno) {
		// TODO Auto-generated method stub
		return dao.insertAnswer(dto, boardno);
	}

	@Override
	public boolean deleteSecond(int groupno, int groupseq, int titletab) {
		// TODO Auto-generated method stub
		return dao.deleteSecond(groupno, groupseq, titletab);
	}

	@Override
	public int deleteFirst(int boardno) {
		// TODO Auto-generated method stub
		return dao.deleteFirst(boardno);
	}

	@Override
	public int deleteThird(int boardno, int groupno, int groupseq) {
		// TODO Auto-generated method stub
		return dao.deleteThird(boardno, groupno, groupseq);
	}

	

}
