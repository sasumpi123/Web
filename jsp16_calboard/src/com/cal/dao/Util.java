package com.cal.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import com.cal.dto.CalDto;

import java.sql.Timestamp;

public class Util {

	// 한자리수 -> 두자리수
	public static String isTwo(String msg) {
		if (msg.length() == 1) {
			return "0" + msg;
		} else {
			return msg;
		}

		/*
		 * return (msg.length==1)?"0"+msg:msg;
		 */
	}

	// font color 변경
	public static String fontColor(int date, int dayOfWeek) {
		int res = (dayOfWeek + date - 1) % 7;
		String color = "";
		if (res == 1) {
			color = "red";
		} else if (res == 0) {
			color = "blue";
		} else {
			color = "black";
		}

		return color;
	}
	
	private String toDates;
	
	public String getToDates() {
		return toDates;
	}
	public void setToDates(String mdate) {
		System.out.println(mdate);
		// yyyyMMddhhmm
		// yyyy-MM-dd hh:mm:00
		String m = mdate.substring(0, 4) + "-" +
				   mdate.substring(4, 6) + "-" +
				   mdate.substring(6, 8) + " " +
				   mdate.substring(8, 10) + ":" +
				   mdate.substring(10) + ":00";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		Timestamp tm = Timestamp.valueOf(m);
		toDates = sdf.format(tm);
	}
	
	public static String getCalView(int date, List<CalDto> clist) {
		String d = isTwo(date+"");
		String res = "";
		
		for(CalDto dto : clist) {
			if(dto.getMdate().substring(6,8).equals(d)){
				res += "<p>"+
				(dto.getTitle().length()>6?dto.getTitle().substring(0,6)+"...":dto.getTitle())+
				"</p>";
			}
			
		}
		return res;
	}

}
