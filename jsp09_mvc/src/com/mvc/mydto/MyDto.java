package com.mvc.mydto;

import java.util.Date;

public class MyDto {

	private int myno;
	private String mywriter;
	private String mytitle;
	private String mycontent;
	private Date mydate;

	public MyDto() {
	}

	public int getMyno() {
		return myno;
	}

	public void setMyno(int myno) {
		this.myno = myno;
	}

	public String getMytitle() {
		return mytitle;
	}

	public void setMytitle(String mytitle) {
		this.mytitle = mytitle;
	}

	public String getMywriter() {
		return mywriter;
	}

	public void setMywriter(String mywriter) {
		this.mywriter = mywriter;
	}

	public String getMycontent() {
		return mycontent;
	}

	public void setMycontent(String mycontent) {
		this.mycontent = mycontent;
	}

	public Date getMydate() {
		return mydate;
	}

	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}

	public MyDto(int myno, String mywriter, String mytitle, String mycontent, Date mydate) {
		super();
		this.myno = myno;
		this.mytitle = mytitle;
		this.mywriter = mywriter;
		this.mycontent = mycontent;
		this.mydate = mydate;
	}

}
