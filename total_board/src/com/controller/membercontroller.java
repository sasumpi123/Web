package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.biz.MemberBiz;
import com.biz.MemberBizImpl;
import com.dto.MemberDto;

@WebServlet("/member")
public class membercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		MemberBiz biz = new MemberBizImpl();
		HttpSession session = request.getSession();
		
		if(command.equals("login")) {
			String mbid = request.getParameter("id");
			String mbpw = request.getParameter("pw");
			MemberDto dto = biz.login(mbid, mbpw);
			
			if(dto != null) {
				// 세션에 로그인 정보 저장
				session.setAttribute("dto", dto);
				session.setMaxInactiveInterval(10 * 60000);
				if(dto.getMbenabled().equals("N")) {
					response.sendRedirect("logindeny.jsp");
				}else if (dto.getMbrole().equals("USER") || dto.getMbrole().equals("MANAGER")) {
					response.sendRedirect("member?command=usermain");
				}else if (dto.getMbrole().equals("ADMIN")) {
					response.sendRedirect("member?command=adminmain");
				}
			}else {
				jsResponse("id, pw를 확인해세요", "index.jsp", response);
			}
			
		}else if(command.equals("usermain")) {
			dispatch("usermain.jsp", request, response);
		}else if(command.equals("adminmain")) {
			dispatch("adminmain.jsp", request, response);
		}
		
		response.getWriter().append("<h1><a href='index.jsp'>응 아니야</h1>");
	
		
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript>");
		out.println("alert('" + msg + "')");
		out.println("location.href='" + url + "'");
		out.println("</script>");
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
}
