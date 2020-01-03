package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyDto;

// ojdbc6.jar - webcontent - web-inf - lib 안에
public class MyDao {

	public List<MyDto> selectList() {
		// 1. driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.driver 연결;");
		} catch (ClassNotFoundException e) {
			System.out.println("[ERROR] : 1 ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2. 계정 연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("2. 계정연결");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[ERROR] : 2");
		}

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
			try {
				// 5. db 종료
				rs.close();
				stmt.close();
				con.close();
				System.out.println("5. db 종료");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("[ERROR] : 5");
			}

		}

		return list;
	}

	public MyDto selectOne(int myno) {

		// 1. driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.driver 연결;");
		} catch (ClassNotFoundException e) {
			System.out.println("[ERROR] : 1 ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2. 계정 연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";

		Connection con = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("2. 계정연결");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[ERROR] : 2");
		}

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
			try {
				// 5. db 종료
				rs.close();
				stmt.close();
				con.close();
				System.out.println("5. db 종료");
			} catch (SQLException e) {
				System.out.println("[ERROR] : 5");
				e.printStackTrace();
			}

		}
		return dto;
	}

	public int insert(MyDto dto) {
		// 1. driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.driver 연결;");
		} catch (ClassNotFoundException e) {
			System.out.println("[ERROR] : 1 ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2. 계정 연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";

		Connection con = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("2. 계정연결");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[ERROR] : 2");
		}

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
			try {
				// 5. db 종료
				pstm.close();
				con.close();
				System.out.println("5. db 종료");
			} catch (SQLException e) {
				System.out.println("[ERROR] : 5");
				e.printStackTrace();
			}

		}
		return res;
	}

	public int update(MyDto dto) {

		// 1. driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.driver 연결;");
		} catch (ClassNotFoundException e) {
			System.out.println("[ERROR] : 1 ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2. 계정 연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";

		Connection con = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("2. 계정연결");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[ERROR] : 2");
		}

		// 3. Query 준비
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MYBOARD SET MYTITLE = ?,MYCONTENT = ? WHERE MYNO =" +dto.getMyno();

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
			try {
				// 5. db 종료
				pstm.close();
				con.close();
				System.out.println("5. db 종료");
			} catch (SQLException e) {
				System.out.println("[ERROR] : 5");
				e.printStackTrace();
			}

		}
		return res;

	}

	public int delete(int myno) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.driver 연결;");
		} catch (ClassNotFoundException e) {
			System.out.println("[ERROR] : 1 ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2. 계정 연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";

		Connection con = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("2. 계정연결");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[ERROR] : 2");
		}

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
			try {
				// 5. db 종료
				rs.close();
				stmt.close();
				con.close();
				System.out.println("5. db 종료");
			} catch (SQLException e) {
				System.out.println("[ERROR] : 5");
				e.printStackTrace();
			}

		}

		return myno;
	}

}
