package com.cal.dao;

import java.util.List;

import com.cal.dto.CalDto;

public interface CalDao {
	
	public int insertCalBoard(CalDto dto);
	public List<CalDto> selectCalList(String id, String yyyyMMdd);
	public List<CalDto> getCalViewList(String id, String yyyyMM);
	public int getCalCount(String id, String yyyyMMdd);

}
