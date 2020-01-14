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

import com.mvc.biz.MVCBiz;
import com.mvc.biz.MVCBizImpl;
import com.mvc.dto.MVCDto;

@WebServlet("/MVCServlet")
public class MVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String command = request.getParameter("command");
		System.out.printf("<%s>\n", command);

		MVCBiz biz = new MVCBizImpl();

		if (command.equals("list")) {

			List<MVCDto> list = biz.selectList();
			request.setAttribute("list", list);

			dispatch("mvclist.jsp", request, response);
		} else if (command.equals("detail")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MVCDto dto = biz.selectOne(myno);
			request.setAttribute("dto", dto);

			dispatch("mvcdetail.jsp", request, response);
		} else if (command.equals("update")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MVCDto dto = biz.selectOne(myno);
			request.setAttribute("dto", dto);
			dispatch("mvcupdate.jsp", request, response);
		} else if (command.equals("updateres")) {
			MVCDto dto = new MVCDto();
			dto.setMytitle(request.getParameter("mytitle"));
			dto.setMycontent(request.getParameter("mycontent"));
			dto.setMyno(Integer.parseInt(request.getParameter("myno")));

			int res = biz.update(dto);
			if (res > 0) {
				System.out.println("수정성공");

				out.println("<script>");
				out.println("alert('수정완료') ");
				out.println("</script>");
				dto = biz.selectOne(dto.getMyno());
				request.setAttribute("dto", dto);
				dispatch("mvcdetail.jsp", request, response);
			}else {
				out.println("<script>");
				out.println("alert('수정실패') ");
				out.println("</script>");
				request.setAttribute("dto", dto);
				dispatch("mvcdetail.jsp", request, response);
			}
		} else if (command.equals("insert")) {
			response.sendRedirect("mvcinsert.jsp");
		} else if (command.equals("insertres")) {
			MVCDto dto = new MVCDto();
			dto.setMywriter(request.getParameter("mywriter"));
			dto.setMytitle(request.getParameter("mytitle"));
			dto.setMycontent(request.getParameter("mycontent"));
			int res = biz.insert(dto);
			if(res>0) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('글 작성 성공') ");
				out.println("</script>");
				response.sendRedirect("mvc.do?command=list");
			}else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('글 작성 실패') ");
				out.println("</script>");
				response.sendRedirect("mvc.do?command=insert");
			}
		}else if (command.equals("delete")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			int res = biz.delete(myno);
			if(res>0) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('글 삭제 성공') ");
				out.println("</script>");
				response.sendRedirect("mvc.do?command=list");
			}else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('글 삭제 실패') ");
				out.println("</script>");
				response.sendRedirect("mvc.do?command=detail&myno="+myno);
			}
		}else if (command.equals("muldelete")) {
			System.out.println("왔니여기");
			String[] seqs = request.getParameterValues("chk");
			// 2. db에서 가져올 데이터가 있는지 확인
			boolean res = biz.multiDelete(seqs);
			if(res==true) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('글 삭제 성공') ");
				out.println("</script>");
				
				List<MVCDto> list = biz.selectList();
				request.setAttribute("list", list);

				dispatch("mvclist.jsp", request, response);
			}else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('글 삭제 실패') ");
				out.println("</script>");
				response.sendRedirect("mvc.do?command=list");
			}
		}
	}
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		doGet(request, response);
	}

}
