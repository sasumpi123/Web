package com.cal.dao;

import com.cal.dto.CalDto;
import static com.cal.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalDaoImpl implements CalDao {

	@Override
	public int insertCalBoard(CalDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res=0;
		String sql = " INSERT INTO CALBOARD VALUES (CALBOARDSEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());
			System.out.println("3. 쿼리문 준비");
			res = pstm.executeUpdate();
			System.out.println("4. 실행 및 결과 리턴");
			
			if(res>0) {
				commit(con);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5. DB 종료");
		}
		return res;
	}
	public CalDto selectCalBoard(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CalDto dto = new CalDto();
		String sql = " SELECT * FROM CALBOARD ";
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. 쿼리문 준비");
			rs = pstm.executeQuery();
			System.out.println("4. 실행 및 결과 리턴");
			while(rs.next()) {
				dto = new CalDto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6));	
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. DB 종료");
		}
		
		return dto;
	}
	@Override
	public List<CalDto> selectCalList(String id, String yyyyMMdd) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<>();
		CalDto dto = new CalDto();
		String sql = " SELECT * FROM CALBOARD WHERE ID = ? AND SUBSTR(MDATE,1,8) = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. 쿼리문 준비");
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			rs = pstm.executeQuery();
			System.out.println("4. 실행 및 결과 리턴");
			while(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setMdate(rs.getString(5));
				dto.setRegdate(rs.getDate(6));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. DB 종료");
		}
		
		return list;
	}

	
	public List<CalDto> getCalViewList(String id, String yyyyMM){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<>();
		
		//234567
		String sql = " select *" + 
				" from(" + 
				" select (row_number() over(partition by substr(mdate,1,8)order by mdate))rn," + 
				" seq, id, title, content, mdate, regdate" + 
				" from calboard " + 
				" where id = ? and substr(mdate,1,6)=?) " +  
				" where rn between 1 and 3 ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMM);
			System.out.println("3. 쿼리 준비");
			rs=pstm.executeQuery();
			System.out.println("4. 실행 및 리턴");
			while(rs.next()) {
				CalDto dto = new CalDto();
				dto.setSeq(rs.getInt(2));
				dto.setId(rs.getString(3));
				dto.setTitle(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setMdate(rs.getString(6));
				dto.setRegdate(rs.getDate(7));
				list.add(dto);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. DB 종료");
		}
		return list;
	}
	@Override
	public int getCalCount(String id, String yyyyMMdd) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = " select count(*) from calboard where id = ? and substr(mdate,1,8) = ? ";
	
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			System.out.println("3. 쿼리문 준비");
			rs = pstm.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
			System.out.println("4. 실행 및 리턴");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. DB 종료");
		}
		System.out.println("res : "+res);
		
		return res;
	}
	
	
}
