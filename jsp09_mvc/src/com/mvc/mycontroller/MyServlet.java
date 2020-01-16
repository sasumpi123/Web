package com.mvc.mycontroller;

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

@WebServlet("/con.do")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");

		MyBiz biz = new MyBizImpl();

		if (command.equals("list")) {
			List<MyDto> list = biz.selectList();

			request.setAttribute("list", list);
			dispatch("mylist.jsp", request, response);
		} else if (command.equals("insert")) {
			response.sendRedirect("insert.jsp");
		} else if (command.equals("insertres")) {
			MyDto dto = new MyDto();
			dto.setMytitle(request.getParameter("mytitle"));
			dto.setMycontent(request.getParameter("mycontent"));
			int res = biz.insert(dto);
			if (res > 0) {
				jsResponse("작성성공", "con.do?command=list", response);
			} else {
				jsResponse("작성실패", "con.do?command=insert", response);
			}

		} else if (command.equals("detail")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MyDto dto = biz.selectOne(myno);
			request.setAttribute("dto", dto);
			dispatch("mydetail.jsp", request, response);
		}else if (command.equals("update")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MyDto dto = biz.selectOne(myno);
			request.setAttribute("dto", dto);
			dispatch("myupdate.jsp", request, response);
		}

		response.getWriter().append("<h1><a href='con.do?command=list'>응 아니야</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		doGet(request, response);
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
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
