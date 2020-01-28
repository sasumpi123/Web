package com.cal.dao;

public class Util {
	
	// 한자리수 -> 두자리수
	public static String isTwo(String msg){
		if(msg.length()==1) {
			return "0"+msg;
		}else {
			return msg;
		}
		
		
		/*
		 * return (msg.length==1)?"0"+msg:msg;
		 */
		
	}

}
