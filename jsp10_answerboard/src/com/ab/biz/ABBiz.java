package com.ab.biz;

import java.util.List;

import com.ab.dto.ABDto;

public interface ABBiz {
	public List<ABDto> selectList();

	public ABDto selectOne(int boardno);

	public int update(ABDto dto);

	public boolean delete(int groupno, int groupseq, int titletab);

	public int insert(ABDto dto);
	
	public int insertAnswer(ABDto dto, int boardno);
}
