package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.mybiz.MyBiz;
import com.mvc.mybiz.MyBizImpl;
import com.mvc.mydto.MyDto;

@WebServlet(urlPatterns = { "/mylist", "/mydetail", "/myinsert", "/myinsertres", "/myupdate","/myupdateres","/mydelete" })
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MyBiz biz;

	private void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		biz = new MyBizImpl();

		String command = request.getRequestURI();
		System.out.printf("[%s]\n", command);

		if (command.endsWith("mylist")) {
			doMyList(request, response);
		} else if (command.endsWith("mydetail")) {
			doMyDetail(request, response);
		} else if (command.endsWith("myinsert")) {
			doMyInsert(request, response);
		} else if (command.endsWith("myinsertres")) {
			doMyInsertRes(request, response);
		} else if (command.endsWith("myupdate")) {
			doMyUpdate(request, response);
		} else if (command.endsWith("myupdateres")) {
			doMyUpdateRes(request,response);
		} else if (command.endsWith("mydelete")) {
			doMyDelete(request,response);
		}
	}


	// 글 전체
	private void doMyList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<MyDto> list = biz.selectList();
		request.setAttribute("list", list);
		dispatch("mylist.jsp", request, response);
	}

	// 글 상세
	private void doMyDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyDto dto = biz.selectOne(myno);
		request.setAttribute("dto", dto);
		dispatch("mydetail.jsp", request, response);
	}

	// 글 작성 페이지로
	private void doMyInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("myinsert.jsp");
	}

	// 글 작성
	private void doMyInsertRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mytitle = request.getParameter("mytitle");
		String mycontent = request.getParameter("mycontent");
		MyDto dto = new MyDto(0, "", mytitle, mycontent, null);
		int res = biz.insert(dto);
		if (res > 0) {
			jsResponse("글 작성 완료", "mylist", response);
		} else {
			jsResponse("글 작성 실패", "myinsert", response);
		}
	}
	// 글 수정 페이지로
	private void doMyUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyDto dto = biz.selectOne(myno);
		request.setAttribute("dto", dto);
		dispatch("myupdate.jsp", request, response);
	}
	// 글 수정
	private void doMyUpdateRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int myno = Integer.parseInt(request.getParameter("myno"));
		String mytitle = request.getParameter("mytitle");
		String mycontent = request.getParameter("mycontent");
		MyDto dto = new MyDto(myno,"",mytitle,mycontent,null);
		int res = biz.update(dto);
		if (res > 0) {
			jsResponse("글 수정 완료", "mydetail?myno="+myno, response);
		} else {
			jsResponse("글 수정 실패", "myupdate?myno="+myno, response);
		}
	}
	// 글 삭제
	private void doMyDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int myno = Integer.parseInt(request.getParameter("myno"));
		int res = biz.delete(myno);
		if (res > 0) {
			jsResponse("글 삭제 완료", "mylist", response);
		} else {
			jsResponse("글 삭제 실패", "mydetail?myno="+myno, response);
		}
		
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('" + msg + "')");
		out.println("location.href='" + url + "'");
		out.println("</script>");
	}

	public MyServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		getRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		getRequest(request, response);
	}

}
