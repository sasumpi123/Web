package com.dto;

import java.util.Date;

public class MyDto {
	// 게시판 db의 글번호, 글 작성자, 글 제목, 글 내용, 글작성 날짜 값들을 저장

	private int board_number;
	private String board_writer;
	private String board_title;
	private String board_content;
	private Date board_date;

	public MyDto() {

	}

	public MyDto(int board_number, String board_writer, String board_title, String board_content, Date board_date) {
		super();
		this.board_number = board_number;
		this.board_writer = board_writer;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_date = board_date;
	}

	public int getBoard_number() {
		return board_number;
	}

	public void setBoard_number(int board_number) {
		this.board_number = board_number;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
}
