package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.JDBCTemplate;
import com.dto.MyDto;

public class MyDao extends JDBCTemplate {

	public List<MyDto> selectList() {

		Connection con = getConnection();
		// 3. Query 준비
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD ORDER BY MYNO DESC ";
		List<MyDto> list = new ArrayList<MyDto>();

		try {
			stmt = con.createStatement();
			System.out.println("3. Query 준비");

			// 4. 실행 및 리턴
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MyDto dto = new MyDto();
				dto.setBoard_number(rs.getInt(1));
				dto.setBoard_writer(rs.getString(2));
				dto.setBoard_title(rs.getString(3));
				dto.setBoard_content(rs.getString(4));
				dto.setBoard_date(rs.getDate(5));

				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[ERROR] : 3, 4");
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}

		return list;
	}
	
	public List<MyDto> paging(int startNum, int endNum) {

		Connection con = getConnection();
		// 3. Query 준비
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM (SELECT ROWNUM as row_num, myboard.* FROM MYBOARD) " + 
				"WHERE row_num>=? AND row_num <= ?" ;
		List<MyDto> list = new ArrayList<MyDto>();

		try {
			pstm = con.prepareStatement(sql);                          
			System.out.println("3. Query 준비");

			pstm.setInt(1, startNum);
			pstm.setInt(2, endNum);
			rs = pstm.executeQuery();
			while (rs.next()) {
				MyDto dto = new MyDto();
				dto.setBoard_number(rs.getInt(2));
				dto.setBoard_writer(rs.getString(3));
				dto.setBoard_title(rs.getString(4));
				dto.setBoard_content(rs.getString(5));
				dto.setBoard_date(rs.getDate(6));
				
				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[ERROR] : 3, 4");
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}

		return list;
	}



	public MyDto selectOne(String board_number) {
		Connection con = getConnection();
		// 3. Query 준비
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD WHERE MYNO = " + board_number;
		MyDto dto = new MyDto();

		try {
			stmt = con.createStatement();
			System.out.println("3. Query 준비");

			// 4. 실행 및 리턴
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dto.setBoard_number(rs.getInt(1));
				dto.setBoard_writer(rs.getString(2));
				dto.setBoard_title(rs.getString(3));
				dto.setBoard_content(rs.getString(4));
				dto.setBoard_date(rs.getDate(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[ERROR] : 3, 4");
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}

		return dto;
	}

	public int delete(String board_number) {
		Connection con = getConnection();
		Statement stmt = null;
		int res = 0;

		String sql = " DELETE FROM MYBOARD WHERE MYNO = " + board_number;

		try {
			stmt = con.createStatement();
			res = stmt.executeUpdate(sql);

			if (res > 0) {
				System.out.println("삭제성공");
			} else {
				System.out.println("삭제실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(stmt);
			close(con);
		}
		return res;
	}

	public int update(MyDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "UPDATE MYBOARD SET MYTITLE = ?,MYCONTENT = ?, MYDATE = SYSDATE WHERE MYNO ="
				+ dto.getBoard_number();

		try {
			pstm = con.prepareStatement(sql);

			pstm.setString(1, dto.getBoard_title());
			pstm.setString(2, dto.getBoard_content());

			res = pstm.executeUpdate();

			if (res > 0) {
				System.out.println("수정성공");
			} else {
				System.out.println("수정실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(pstm);
			close(con);
		}

		return res;
	}

	public int insert(MyDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MYBOARD VALUES (MYSEQ.NEXTVAL,?,?,?,SYSDATE) ";

		try {
			pstm = con.prepareStatement(sql);

			pstm.setString(1, dto.getBoard_writer());
			pstm.setString(2, dto.getBoard_title());
			pstm.setString(3, dto.getBoard_content());

			res = pstm.executeUpdate();

			if (res > 0) {
				System.out.println("삽입 성공");
			} else {
				System.out.println("삽입 실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(pstm);
			close(con);
		}
		return res;
	}

	public int[] muldelete(String[] number) {
		Connection con = getConnection();
		PreparedStatement pstm = null;

		int[] res = null;

		String sql = " DELETE FROM MYBOARD WHERE MYNO = ? ";

		try {
			pstm = con.prepareStatement(sql);
			for (int i = 0; i < number.length; i++) {
				pstm.setString(1, number[i]);
				pstm.addBatch();
			}
			res = pstm.executeBatch();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}

		return res;
	}

}
