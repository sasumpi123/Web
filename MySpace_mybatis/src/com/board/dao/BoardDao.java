package com.board.dao;

import java.util.List;

import com.answer.dto.ReplyDto;
import com.board.dto.BoardDto;
import com.paging.dto.PagingDto;

public interface BoardDao {
	
	String SELECT_LIST_SQL = " SELECT B.* FROM ( SELECT A.*, ROWNUM AS RNUM  FROM ( SELECT * FROM BOARD ORDER BY SEQ DESC )A )B WHERE RNUM BETWEEN ? AND ? ORDER BY REGROUP DESC, RESTEP ";
	String SELECT_ONE_SQL = " SELECT * FROM BOARD WHERE SEQ = ? ";
	String INSERT_SQL = " INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
	String UPDATE_SQL = " UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE SEQ = ? ";
	String DELETE_SQL = " DELETE FROM BOARD WHERE SEQ = ? ";
	
	public List<ReplyDto> selectList(PagingDto dto);
	public BoardDto selectOne(int seq);
	public int insert(BoardDto dto);
	public int update(BoardDto dto);
	public int delete(int seq);
}
