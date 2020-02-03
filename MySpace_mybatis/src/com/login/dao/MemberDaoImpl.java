package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.dto.MemberDto;
import static com.login.db.JDBCTemplate.*;

public class MemberDaoImpl implements MemberDao {

	@Override
	public MemberDto login(String id, String pw) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto dto = null;
		
		try {
			pstm = con.prepareStatement(LOGIN_SQL);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new MemberDto(rs.getString(1),
									rs.getString(2),
									rs.getString(3),
									rs.getString(4),
									rs.getString(5),
									rs.getString(6),
									rs.getString(7),
									rs.getString(8));
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
	}

	@Override
	public int insertUser(MemberDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(INSERT_USER_SQL);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getPw());
			pstm.setString(3, dto.getName());
			pstm.setString(4, dto.getAddr());
			pstm.setString(5, dto.getPhone());
			pstm.setString(6, dto.getEmail());
			
			res = pstm.executeUpdate();
			
			if(res > 0 ) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}

	@Override
	public MemberDto idChk(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM MEMBER WHERE ID = ? ";
		MemberDto dto = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto = new MemberDto();
				dto.setId(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		return dto;
	}

	@Override
	public boolean emailChk(String email) {
		
		return false;
	}

	@Override
	public List<MemberDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				MemberDto dto = new MemberDto(rs.getString(1),
											  rs.getString(2),
											  rs.getString(3),
											  rs.getString(4),
											  rs.getString(5),
											  rs.getString(6),
											  rs.getString(7),
											  rs.getString(8));
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		return list;
	}

	@Override
	public List<MemberDto> selectEnabled() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		try {
			pstm = con.prepareStatement(SELECT_ENABLED_SQL);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				MemberDto dto = new MemberDto(rs.getString(1),
											  rs.getString(2),
											  rs.getString(3),
											  rs.getString(4),
											  rs.getString(5),
											  rs.getString(6),
											  rs.getString(7),
											  rs.getString(8));
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		return list;
	}

	@Override
	public int updateRole(String id, String role) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_ROLE_SQL);
			pstm.setString(1, role);
			pstm.setString(2, id);
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}

	@Override
	public MemberDto selectOne(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto dto = null;
		
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new MemberDto(rs.getString(1),
									rs.getString(2),
									rs.getString(3),
									rs.getString(4),
									rs.getString(5),
									rs.getString(6),
									rs.getString(7),
									rs.getString(8));
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
	}

	@Override
	public int updateUser(MemberDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_USER_SQL);
			pstm.setString(1, dto.getPw());
			pstm.setString(2, dto.getName());
			pstm.setString(3, dto.getAddr());
			pstm.setString(4, dto.getPhone());
			pstm.setString(5, dto.getEmail());
			pstm.setString(6, dto.getId());
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}

	@Override
	public int deleteUser(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(DELETE_USER_SQL);
			pstm.setString(1, id);
			res = pstm.executeUpdate();
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}

}
