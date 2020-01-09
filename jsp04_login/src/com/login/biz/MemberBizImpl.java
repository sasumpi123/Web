package com.login.biz;

import java.util.List;

import com.login.dao.MemberDao;
import com.login.dao.MemberDaoImpl;
import com.login.dto.MemberDto;

public class MemberBizImpl implements MemberBiz {
	
	private MemberDao dao = new MemberDaoImpl();

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
	public int updateRole(int myno, String role) {
		// TODO Auto-generated method stub
		return dao.updateRole(myno, role);
	}

	@Override
	public MemberDto login(String myid, String mypw) {
		// TODO Auto-generated method stub
		return dao.login(myid, mypw);
	}

	@Override
	public MemberDto idChk(String myid) {
		// TODO Auto-generated method stub
		return dao.idChk(myid);
	}

	@Override
	public int insertUser(MemberDto dto) {
		// TODO Auto-generated method stub
		return dao.insertUser(dto);
	}

	@Override
	public MemberDto selectUser(int myno) {
		// TODO Auto-generated method stub
		return dao.selectUser(myno);
	}

	@Override
	public int updateUser(MemberDto dto) {
		// TODO Auto-generated method stub
		return dao.updateUser(dto);
	}

	@Override
	public int deleteUser(int myno) {
		// TODO Auto-generated method stub
		return dao.deleteUser(myno);
	}

}
