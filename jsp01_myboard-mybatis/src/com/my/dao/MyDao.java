package com.my.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.dto.MyDto;

public class MyDao extends SqlMapConfig {

	private String namespace = "muldel.";

	public List<MyDto> selectList() {
		List<MyDto> list = null;

		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace + "selectList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : selectList");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	public MyDto selectOne(int myno) {

		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();
		MyDto dto = session.selectOne("com.my.mapper.selectOne", myno);
		session.close();

		return dto;
	}

	public int insert(MyDto dto) {
		int res = 0;

		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			res = session.insert(namespace + "insert",dto);
			
			if(res>0) {
				session.commit();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : insert");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}

	public int update(MyDto dto) {
		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession sqlSession = sqlSessionFactory.openSession();
		int res = sqlSession.update("com.my.mapper.update", dto);
		if (res > 0) {
			sqlSession.commit();
		}
		sqlSession.close();

		return res;
	}

	public int delete(int myno) {
		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int res = sqlSession.delete("com.my.mapper.delete", myno);
		if (res > 0) {
			sqlSession.commit();
		}
		sqlSession.close();

		return res;
	}

	public int selectDelete(String[] no) {
		int res = 0;
		
		SqlSession session = null;
		Map<String, String[]> map = new HashMap<>();
		map.put("mynos",no);
		
		try {
			session = getSqlSessionFactory().openSession();
			res = session.delete(namespace+"muldel",map);
			if(res == no.length) {
				session.commit();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[error] : muldel");
			e.printStackTrace();
		}finally {
			session.close();
		}

		return res;
	}

}