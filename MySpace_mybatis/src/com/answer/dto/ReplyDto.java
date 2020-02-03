package com.answer.dto;

import java.util.Date;

public class ReplyDto {
	private int seq;
	private int regroup;
	private int restep;
	private int retab;
	private String writer;
	private String title;
	private String content;
	private Date regdate;
	private String delflag;
	
	
	public ReplyDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ReplyDto(int seq, String writer, String title, String content,
			Date regdate, int regroup, int restep, int retab, String delflag) {
		super();
		this.seq = seq;
		this.regroup = regroup;
		this.restep = restep;
		this.retab = retab;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.delflag = delflag;
	}
	
	
	public int getseq() {
		return seq;
	}
	public void setseq(int seq) {
		this.seq = seq;
	}
	public int getRegroup() {
		return regroup;
	}
	public void setRegroup(int regroup) {
		this.regroup = regroup;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public int getRetab() {
		return retab;
	}
	public void setRetab(int retab) {
		this.retab = retab;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
	
}
