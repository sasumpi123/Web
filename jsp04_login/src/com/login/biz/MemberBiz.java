package com.login.biz;

import java.util.List;

import com.login.dto.MemberDto;

public interface MemberBiz {
	
	// 관리자(Admin) 기능
	// 1. 회원 전체 정보(탈퇴회원 포함)
	public List<MemberDto> selectList();
	// 2. 회원 전체 정보(가입된 회원들만) : myenabled = y
	public List<MemberDto> selectEnabled();
	// 3. 회원 등급 조정
	public int updateRole(int myno, String role);
	
	// 사용자(user) 기능
	// 1. 로그인
	public MemberDto login(String myid, String mypw);
	
	// 2. 회원가입
	// 2-1. 중복체크	
	public MemberDto idChk(String myid);
	// 2-2. 회원가입
	public int insertUser(MemberDto dto);
	// 3. 내 정보 조회
	public MemberDto selectUser(int myno);
	// 4. 내 정보 수정
	public int updateUser(MemberDto dto);
	// 5. 회원 탈퇴 : update myenabled = 'n'
	public int deleteUser(int myno);



}
