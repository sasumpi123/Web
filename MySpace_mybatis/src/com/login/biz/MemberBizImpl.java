package com.login.biz;

import java.util.List;

import com.login.dao.MemberDao;
import com.login.dao.MemberDaoImpl;
import com.login.dto.MemberDto;

public class MemberBizImpl implements MemberBiz {
	
	private MemberDao dao = new MemberDaoImpl();

	@Override
	public MemberDto login(String id, String pw) {
		// TODO Auto-generated method stub
		return dao.login(id, pw);
	}

	@Override
	public int insertUser(MemberDto dto) {
		// TODO Auto-generated method stub
		return dao.insertUser(dto);
	}

	@Override
	public MemberDto idChk(String id) {
		// TODO Auto-generated method stub
		return dao.idChk(id);
	}

	@Override
	public boolean emailChk(String email) {
		// TODO Auto-generated method stub
		return dao.emailChk(email);
	}

	@Override
	public List<MemberDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public List<MemberDto> selectEnabled() {
		// TODO Auto-generated method stub
		return dao.selectEnabled();
	}

	@Override
	public int updateRole(String id, String role) {
		// TODO Auto-generated method stub
		return dao.updateRole(id, role);
	}

	@Override
	public MemberDto selectOne(String id) {
		// TODO Auto-generated method stub
		return dao.selectOne(id);
	}

	@Override
	public int updateUser(MemberDto dto) {
		// TODO Auto-generated method stub
		return dao.updateUser(dto);
	}

	@Override
	public int deleteUser(String id) {
		// TODO Auto-generated method stub
		return dao.deleteUser(id);
	}

}
