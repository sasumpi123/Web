package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ScopeController")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ScopeController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("controller 도착");

		String requestId = (String) request.getAttribute("requestId");
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("sessionId");
		ServletContext application = getServletContext();
		String applicationId = (String) application.getAttribute("applicationId");

		System.out.println("requestId : " + requestId);
		System.out.println("sessionId : " + sessionId);
		System.out.println("applicationId : " + applicationId);

		String param = request.getParameter("req");
		System.out.println("request parameter : " + param);

		PrintWriter out = response.getWriter();
		/*
		   out.println("<h1>response</h1>"); out.println("<table border='2'> ");
		   out.println("<tr><th>scope</th><th>값</th></tr>");
		   out.println("<tr><th>pageId</th><th>null</th></tr>");
		   out.println("<tr><th>requestId</th><th>"+requestId+"</th></tr>");
		   out.println("<tr><th>sessionId</th><th>"+sessionId+"</th></tr>");
		   out.println("<tr><th>applicationId</th><th>"+applicationId+"</th></tr>");
		   out.println("</table>");
		 */
		
		RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");
		request.setAttribute("requestId", "servlet 에서 보내준 request");
		dispatch.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		doGet(request, response);
	}

}
