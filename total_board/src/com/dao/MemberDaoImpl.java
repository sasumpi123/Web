package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dto.MemberDto;
import com.dao.SqlMapConfig;

public class MemberDaoImpl extends SqlMapConfig implements MemberDao{
	private String namespace = "member.";

	@Override
	public List<MemberDto> selectList() {
		List<MemberDto> list = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace + "selectMember");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : selectMember");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List<MemberDto> selectEnabled() {
		List<MemberDto> list = null;

		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace + "selectMemberEnabled");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : selectMemberEnabled");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public int updateRole(int mbno, String mbrole) {
		MemberDto dto = new MemberDto();
		int res = 0;
		dto.setMbno(mbno);
		dto.setMbrole(mbrole);
		
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			res = session.update(namespace + "updateMemberRole",dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : updateMemberRole");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public MemberDto login(String mbid, String mbpw) {
		MemberDto dto = new MemberDto();
		Map<String, String> map = new HashMap<>();
		map.put("mbid", mbid);
		map.put("mbpw", mbpw);
		
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace + "selectOneMemberLogin",map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : selectOneMemberLogin");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}

	@Override
	public MemberDto idChk(String mbid) {
		MemberDto dto = new MemberDto();
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace + "selectOneMemberIdChk",mbid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : selectOneMemberIdChk");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}

	@Override
	public int insertUser(MemberDto dto) {
		int res = 0;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			res = session.insert(namespace + "insertMemberUser",dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : insertMemberUser");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public MemberDto selectUser(int mbno) {
		
		MemberDto dto = new MemberDto();
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace + "selectOneMemberUser",mbno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : selectOneMemberUser");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dto;
	}

	@Override
	public int updateUser(MemberDto dto) {
		int res = 0;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			res = session.update(namespace + "updateMemberUser",dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : updateMemberUser");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public int deleteUser(int mbno) {
		int res = 0;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			res = session.update(namespace + "deleteMemberuser",mbno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : deleteMemberuser");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}

}
