package com.mvc.mydao;

import java.util.List;

import com.mvc.mydto.MyDto;

public interface MyDao {
	public List<MyDto> selectList();

	public MyDto selectOne(int myno);

	public int insert(MyDto dto);

	public int update(MyDto dto);

	public int delete(int myno);

	public boolean multiDelete(String[] myno);

}
