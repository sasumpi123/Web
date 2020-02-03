package com.board.biz;

import java.util.List;

import com.answer.dto.ReplyDto;
import com.board.dto.BoardDto;
import com.paging.dto.PagingDto;

public interface BoardBiz {
	
	public List<ReplyDto> selectList(PagingDto dto);
	public BoardDto selectOne(int seq);
	public int insert(BoardDto dto);
	public int update(BoardDto dto);
	public int delete(int seq);

}
