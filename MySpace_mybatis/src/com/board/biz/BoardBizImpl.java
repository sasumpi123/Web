package com.board.biz;

import java.util.List;

import com.answer.dto.ReplyDto;
import com.board.dao.BoardDao;
import com.board.dao.BoardDaoImpl;
import com.board.dto.BoardDto;
import com.paging.dto.PagingDto;

public class BoardBizImpl implements BoardBiz {
	
	private BoardDao dao = new BoardDaoImpl();

	@Override
	public List<ReplyDto> selectList(PagingDto dto) {
		int page = dto.getPage();
		int rows = dto.getRows();
		
		int to = rows * (page - 1) + 1;
		int from = rows * page;
		
		dto.setTo(to);
		dto.setFrom(from);
		
		return dao.selectList(dto);
	}

	@Override
	public BoardDto selectOne(int seq) {
		// TODO Auto-generated method stub
		return dao.selectOne(seq);
	}

	@Override
	public int insert(BoardDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(BoardDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		// TODO Auto-generated method stub
		return dao.delete(seq);
	}

}
