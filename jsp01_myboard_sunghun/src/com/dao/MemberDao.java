package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.JDBCTemplate;
import com.dto.MemberDto;

public class MemberDao extends JDBCTemplate{

	public int checkId(MemberDto dto) {
		Connection con = getConnection();

		// 3. Query 준비
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " SELECT * FROM MEMBER WHERE (MEMBER_ID = ?) AND (MEMBER_PASSWORD = ?) ";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMember_id());
			pstm.setString(2, dto.getMember_password());
			
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
	
	public int signIn(MemberDto dto) {
		Connection con = getConnection();

		// 3. Query 준비
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MEMBER VALUES (MEMBERSEQ.NEXTVAL,?,?)";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMember_id());
			pstm.setString(2, dto.getMember_password());
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
	
}
