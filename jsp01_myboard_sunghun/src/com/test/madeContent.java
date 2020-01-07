package com.test;

import com.dto.MyDto;
import com.dao.MyDao;

public class madeContent {

	public static void main(String[] args) {
		MyDto dto = new MyDto();
		MyDao dao = new MyDao();

		dto.setMyname("123");
		dto.setMytitle("123");
		dto.setMycontent("123");
		for (int i = 0; i < 350; i++) {
			dao.insert(dto);
		}

	}

}
