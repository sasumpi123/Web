package com.login.dao;

import static com.login.db.JDBCTemplate.getConnection;
import static com.login.db.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.dto.MemberDto;

public class MemberDaoImpl implements MemberDao {

	@Override
	public List<MemberDto> selectList() {
		Connection con = getConnection();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MemberDto> list = new ArrayList<MemberDto>();
		String sql = " SELECT * FROM MYMEMBER ORDER BY MYNO DESC";
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. 쿼리 준비");
			rs = pstm.executeQuery();

			while (rs.next()) {
				MemberDto dto = new MemberDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
				list.add(dto);
			}
			System.out.println("4. 실행 및 리턴");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. DB 종료");
		}

		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<MemberDto> selectEnabled() {
		// TODO Auto-generated method stub
		Connection con = getConnection();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MemberDto> list = new ArrayList<MemberDto>();
		String sql = " SELECT * FROM MYMEMBER WHERE MYENABLED = 'Y' ORDER BY MYNO DESC ";
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. 쿼리 준비");
			rs = pstm.executeQuery();

			while (rs.next()) {

				MemberDto dto = new MemberDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
				list.add(dto);
			}
			System.out.println("4. 실행 및 리턴");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. DB 종료");
		}

		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public int updateRole(int myno, String role) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = " UPDATE MYMEMBER SET MYROLE = ? WHERE MYNO = ? ";
		int res =0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, role);
			pstm.setInt(2, myno);
			System.out.println("쿼리문 준비");
			
			res = pstm.executeUpdate();
			System.out.println("실행 및 리턴");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5. DB 종료");
		}
		
		return res;
	}

	@Override
	public MemberDto login(String myid, String mypw) {
		Connection con = getConnection();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto dto = null;
		String sql = " SELECT * FROM MYMEMBER WHERE MYID = ? AND MYPW = ? AND MYENABLED = ?";

		try {
			pstm = con.prepareStatement(sql);

			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			pstm.setString(3, "Y");
			System.out.println("3. 쿼리준비");

			rs = pstm.executeQuery();
			while (rs.next()) {
				dto = new MemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
			}
			System.out.println("4. 실행 및 리턴");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.DB 종료");
		}

		return dto;
	}

	@Override
	public MemberDto idChk(String myid) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto dto = null;
		return dto;
	}

	@Override
	public int insertUser(MemberDto dto) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res=0;
		return res;
	}

	@Override
	public MemberDto selectUser(int myno) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto dto = new MemberDto();
		
		String sql = " SELECT * FROM MYMEMBER WHERE MYNO = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto = new MemberDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}	
		return dto;
	}

	@Override
	public int updateUser(MemberDto dto) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res=0;
		return res;
	}

	@Override
	public int deleteUser(int myno) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res =0;
		return res;
	}

}
