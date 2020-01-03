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


// ojdbc6.jar - webcontent - web-inf - lib 안에
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
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString("MYNAME"));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));

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

	public MyDto selectOne(int myno) {

		Connection con = getConnection();

		// 3. Query 준비
		Statement stmt = null;
		ResultSet rs = null;

		String sql = " SELECT * FROM MYBOARD WHERE MYNO = " + myno;
		MyDto dto = new MyDto();

		try {

			System.out.println("3.Query 준비 : " + sql);

			// 4. 실행 및 리턴
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString("MYNAME"));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
			}

			System.out.println("실행 및 리턴");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return dto;
	}

	public int insert(MyDto dto) {
		Connection con = getConnection();

		// 3. Query 준비
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MYBOARD VALUES (MYSEQ.NEXTVAL,?,?,?,SYSDATE)";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			System.out.println("3.Query 준비 : " + sql);

			// 4. 실행 및 리턴
			res = pstm.executeUpdate();
			System.out.println("실행 및 리턴");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}

	public int update(MyDto dto) {

		Connection con = getConnection();

		// 3. Query 준비
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MYBOARD SET MYTITLE = ?,MYCONTENT = ? WHERE MYNO =" + dto.getMyno();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			System.out.println("3.Query 준비 : " + sql);

			// 4. 실행 및 리턴
			res = pstm.executeUpdate();
			System.out.println("실행 및 리턴");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;

	}

	public int delete(int myno) {

		Connection con = getConnection();

		// 3. Query 준비
		Statement stmt = null;
		ResultSet rs = null;

		String sql = " DELETE FROM MYBOARD WHERE MYNO = " + myno;
		MyDto dto = new MyDto();

		try {

			System.out.println("3.Query 준비 : " + sql);

			// 4. 실행 및 리턴
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString("MYNAME"));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
			}

			System.out.println("실행 및 리턴");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}

		return myno;
	}

}