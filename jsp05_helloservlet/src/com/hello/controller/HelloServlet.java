package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// URL 패턴
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloServlet() {
		System.out.println("Servlet 생성자!!");
	}

	private String initParam;
	private String contextParam;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet init!");

		contextParam = config.getServletContext().getInitParameter("jdbcurl");
		initParam = config.getInitParameter("driver");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 깨짐 방지용 최대한 항상 써주기
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		System.out.println("GET 방식으로 넘어옴");
		System.out.println("initParam : " + initParam);

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		System.out.println("POST방식으로 넘어옴");
		System.out.println("initParam : " + initParam);
		System.out.println("contextParam : " + contextParam);

		String command = request.getParameter("command");
		System.out.println("command : " + command);

		PrintWriter out = response.getWriter();
		out.println("<h1 style='background-color:skyblue'>HelloServlet</h1>");
		out.println("<h3>init-service-doGet/doPost-destroy</h3>");
		out.println("<a href='home.html'>돌아가기</a>");
		// doGet(request, response); // 지워줘야함
	}

	@Override
	public void destroy() {
		System.out.println("servlet destroy");
	}

}
