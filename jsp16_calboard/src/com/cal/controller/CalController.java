package com.cal.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;
import com.cal.dao.CalDaoImpl;
import com.cal.dao.Util;
import com.cal.dto.CalDto;

@WebServlet("/calendar.do")
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println("command = " + command);

		if (command.equals("calendar")) {
			response.sendRedirect("calendar.jsp");
		} else if (command.equals("insertcal")) {
			String year = request.getParameter("year");
			String month = Util.isTwo(request.getParameter("month"));
			String date = Util.isTwo(request.getParameter("date"));
			String hour = Util.isTwo(request.getParameter("hour"));
			String min = Util.isTwo(request.getParameter("min"));
			String mDate = year + month + date + hour + min;
			
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			CalDto dto = new CalDto(0,id,title,content,mDate,null);
			CalDao dao = new CalDaoImpl();
			int res = dao.insertCalBoard(dto);
			if(res>0) {
				System.out.println("성공");
				response.sendRedirect("calendar.jsp");
			}else {
				System.out.println("실패");
			}
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}

}
