package com.mvc.mydao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.mvc.mydb.JDBCTemplate.*;

import com.mvc.mydto.MyDto;

public class MyDaoImpl implements MyDao {

	@Override
	public List<MyDto> selectList() {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM MVCBOARD ";
		List<MyDto> list = new ArrayList<>();

		try {
			stmt = con.createStatement();
			System.out.println("3: 쿼리문 준비");
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MyDto dto = new MyDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MyDto selectOne(int myno) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		MyDto dto = new MyDto();
		String sql = " SELECT * FROM MVCBOARD WHERE MYNO = " + myno;

		try {
			stmt = con.createStatement();
			System.out.println("쿼리문 준비");
			rs = stmt.executeQuery(sql);
			System.out.println("실행 및 결과 리턴");

			while (rs.next()) {
				dto.setMyno(rs.getInt(1));
				dto.setMywriter(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
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
	public int insert(MyDto dto) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MVCBOARD VALUES (MVCSEQ.NEXTVAL, '일단해놈', ?, ?, SYSDATE) ";

		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			System.out.println("쿼리문 준비" + sql);

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
	public int update(MyDto dto) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MVCBOARD SET MYTITLE = ?, MYCONTENT = ? WHERE MYNO = " + dto.getMyno();

		try {
			pstm = con.prepareStatement(sql);
			System.out.println("쿼리준비");
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
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
	public int delete(int myno) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = " DELETE FROM MVCBOARD WHERE MYNO = ?";
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("쿼리 준비");
			res = pstm.executeUpdate();
			System.out.println("실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}

		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public boolean multiDelete(String[] myno) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;

		String sql = " DELETE FROM MVCBOARD WHERE MYNO = ? ";
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("쿼리문 준비");

			for (int i = 0; i < myno.length; i++) {
				pstm.setString(1, myno[i]);
				pstm.addBatch(); // db에 전달하기 전에 메모리에 적재
			}
			cnt = pstm.executeBatch();

			for (int i = 0; i < cnt.length; i++) {
				if (cnt[i] == -2) { // 성공하면 -2 실패하면 -3
					res++;
				}
			}
			if (res == myno.length) {
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
		return (res == myno.length) ? true : false;

	}

}
