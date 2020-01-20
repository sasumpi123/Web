package com.ab.dao;

import java.util.List;

import com.ab.dto.ABDto;

public interface ABDao {

	public List<ABDto> selectList();

	public ABDto selectOne(int boardno);

	public int update(ABDto dto);

	

	public int insert(ABDto dto);
	
	public int insertAnswer(ABDto dto, int boardno);
	
	public boolean delete(int groupno, int groupseq, int titletab);
}
