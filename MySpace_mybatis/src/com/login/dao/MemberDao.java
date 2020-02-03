package com.login.dao;

import java.util.List;

import com.login.dto.MemberDto;

public interface MemberDao {
	
	
	String LOGIN_SQL = " SELECT * FROM MEMBER WHERE ID = ? AND PW = ? ";
	String INSERT_USER_SQL = " INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, 'Y', 'USER') ";
	String SELECT_LIST_SQL = " SELECT * FROM MEMBER ";
	String SELECT_ENABLED_SQL = " SELECT * FROM MEMBER WHERE ENABLED = 'Y' ";
	String UPDATE_ROLE_SQL = " UPDATE MEMBER SET ROLE = ? WHERE ID = ? ";
	String SELECT_ONE_SQL = " SELECT * FROM MEMBER WHERE ID = ? ";
	String UPDATE_USER_SQL = " UPDATE MEMBER SET PW = ?, NAME = ?, ADDR = ?, PHONE = ?, EMAIL = ? WHERE ID = ? ";
	String DELETE_USER_SQL = " UPDATE MEMBER SET ENABLED = 'N' WHERE ID = ? ";
	
	
	
	// 로그인
	public MemberDto login(String id, String pw);
	// 회원가입
	public int insertUser(MemberDto dto);
	// 중복체크
	public MemberDto idChk(String id);
	public boolean emailChk(String email);
	
	// admin
	// 전체 회원 조회
	public List<MemberDto> selectList();
	// enabled = 'y'인 회원 조회
	public List<MemberDto> selectEnabled();
	// 등급 변경
	public int updateRole(String id, String role);
	
	// user
	// 내 정보 조회
	public MemberDto selectOne(String id);
	// 내 정보 수정
	public int updateUser(MemberDto dto);
	// 회원 탈퇴
	public int deleteUser(String id);

}
