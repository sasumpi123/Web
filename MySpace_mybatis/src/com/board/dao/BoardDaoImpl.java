package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.answer.dto.ReplyDto;
import com.board.db.SqlMapConfig;
import com.board.dto.BoardDto;
import com.paging.dto.PagingDto;

public class BoardDaoImpl extends SqlMapConfig implements BoardDao {
	
	String namespace = "board.";

	@Override
	public List<ReplyDto> selectList(PagingDto dto) {
		List<ReplyDto> list = null;
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionactory().openSession();
			list = session.selectList(namespace+"selectList", dto);
		} catch (Exception e) {
			System.out.println("[error] : selectList");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public BoardDto selectOne(int seq) {
		BoardDto dto = null;
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionactory().openSession();
			dto = session.selectOne(namespace+"selectOne", seq);
		} catch (Exception e) {
			System.out.println("[error] : selectOne");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}

	@Override
	public int insert(BoardDto dto) {
		int res = 0;
		
		SqlSession session = null;
		
		
		
		try {
			session = getSqlSessionactory().openSession();
			System.out.println("dto.title : " + dto.getTitle());
			System.out.println("dto.writer : " + dto.getWriter());
			System.out.println("dto.content : " + dto.getContent());
			res = session.insert(namespace+"insert", dto);
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("[error] : insert");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public int update(BoardDto dto) {
		int res = 0;
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionactory().openSession();
			res = session.update(namespace+"update", dto);
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("[error] : update");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public int delete(int seq) {
		int res = 0;
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionactory().openSession();
			res = session.delete(namespace+"delete", seq);
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("[error] : delete");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

}
