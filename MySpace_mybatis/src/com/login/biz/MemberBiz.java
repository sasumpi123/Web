package com.login.biz;

import java.util.List;

import com.login.dto.MemberDto;

public interface MemberBiz {
	
	public MemberDto login(String id, String pw);
	public int insertUser(MemberDto dto);
	public MemberDto idChk(String id);
	public boolean emailChk(String email);
	
	public List<MemberDto> selectList();
	public List<MemberDto> selectEnabled();
	public int updateRole(String id, String role);
	
	public MemberDto selectOne(String id);
	public int updateUser(MemberDto dto);
	public int deleteUser(String id);

}
