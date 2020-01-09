package com.mvc.dao;

import static com.mvc.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dao.MVCDao;
import com.mvc.dto.MVCDto;

public class MVCDaoImpl implements MVCDao {

	@Override
	public List<MVCDto> selectList() {
		Connection con = getConnection();

		
		Statement stmt = null;
		ResultSet rs = null;
		List<MVCDto> list = new ArrayList<>();

		try {
			stmt = con.createStatement();
			System.out.println("쿼리문 준비");

			rs = stmt.executeQuery(SELECT_LIST_SQL);
			System.out.println("실행 및 결과 리턴");
			while (rs.next()) {
				MVCDto dto = new MVCDto();

				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
			System.out.println("DB종료");

		}

		return list;
	}

	@Override
	public MVCDto selectOne(int seq) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		MVCDto dto = new MVCDto();

		try {
			stmt = con.createStatement();
			System.out.println("쿼리문 준비");
			rs = stmt.executeQuery(SELECT_ONE_SQL + seq);
			System.out.println("실행 및 결과 리턴");

			while (rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5)); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
			System.out.println("DB종료");
		}

		// TODO Auto-generated method stub
		return dto;
	}

	@Override
	public int insert(MVCDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("쿼리문 준비" + INSERT_SQL);

			res = pstm.executeUpdate();
			System.out.println("실행 및 결과 리턴");
			if (res > 0) {
				// commit(con);
				System.out.println("commit 완료");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("DB종료");
		}
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public int update(MVCDto dto) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			System.out.println("쿼리준비");
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());

			res = pstm.executeUpdate();
			System.out.println("실행 및 결과 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("DB 종료");
		}

		return res;
	}

	@Override
	public int delete(String seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			pstm.setString(1,seq);
			System.out.println("쿼리 준비");
			res = pstm.executeUpdate();
			System.out.println("실행 및 리턴");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public boolean multiDelete(String[] seqs) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;

		try {
			pstm = con.prepareStatement(DELETE_SQL);
			System.out.println("쿼리문 준비");

			for (int i = 0; i < seqs.length; i++) {
				pstm.setString(1, seqs[i]);
				pstm.addBatch(); // db에 전달하기 전에 메모리에 적재
			}
			cnt = pstm.executeBatch();

			for (int i = 0; i < cnt.length; i++) {
				if (cnt[i] == -2) { // 성공하면 -2 실패하면 -3
					res++;
				}
			}
			if (res == seqs.length) {
				commit(con);
			} else {
				rollback(con);
				res = 0;
			}
			System.out.println("실행 및 결과 리턴");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("DB 종료");
		}

		// TODO Auto-generated method stub
		return (res == seqs.length) ? true : false;
	}

}
