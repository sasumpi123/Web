package com.ab.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ab.biz.ABBiz;
import com.ab.biz.ABBizImpl;
import com.ab.dto.ABDto;

@WebServlet(urlPatterns = { "/mylist", "/mydetail", "/myinsert", "/myinsertres", "/myupdate", "/myupdateres",
		"/insertanswer", "/insertanswerres", "/mydeletefirst", "/mydeletesecond", "/mydeletethird" })
public class ABServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ABBiz biz;

	private void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		biz = new ABBizImpl();

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
			doMyUpdateRes(request, response);
		} else if (command.endsWith("insertanswer")) {
			doInsertAnswer(request, response);
		} else if (command.endsWith("insertanswerres")) {
			doInsertAnswerRes(request, response);
		} else if (command.endsWith("mydeletefirst")) {
			doMyDeleteFirst(request, response);
		} else if (command.endsWith("mydeletesecond")) {
			doMyDeleteSecond(request, response);
		} else if (command.endsWith("mydeletethird")) {
			doMyDeleteThird(request, response);
		}

	}

	private void doMyDeleteThird(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		ABDto dto = biz.selectOne(boardno);
		int res = biz.deleteThird(boardno, dto.getGroupno(), dto.getGroupseq());
		if (res>0) {
			jsResponse("글 삭제 완료", "mylist", response);
		} else {
			jsResponse("글 삭제 실패", "mydetail?boardno=" + boardno, response);
		}
	}

	private void doMyDeleteFirst(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		int res = biz.deleteFirst(boardno);
		if (res>0) {
			jsResponse("글 삭제 완료", "mylist", response);
		} else {
			jsResponse("글 삭제 실패", "mydetail?boardno=" + boardno, response);
		}

	}

	private void doMyDeleteSecond(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		ABDto dto = new ABDto();
		dto = biz.selectOne(boardno);
		int groupno = dto.getGroupno();
		int groupseq = dto.getGroupseq();
		int titletab = dto.getTitletab();
		boolean res = biz.deleteSecond(groupno, groupseq, titletab);
		if (res == true) {
			jsResponse("글 삭제 완료", "mylist", response);
		} else {
			jsResponse("글 삭제 실패", "mydetail?boardno=" + boardno, response);
		}
	}

	private void doInsertAnswerRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		ABDto dto = new ABDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);

		int res = biz.insertAnswer(dto, boardno);
		if (res > 0) {
			jsResponse("답글 작성 완료", "mylist", response);
		} else {
			jsResponse("답글 작성 실패", "myinsert", response);
		}

	}

	private void doInsertAnswer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		ABDto dto = biz.selectOne(boardno);
		request.setAttribute("dto", dto);
		dispatch("insertanswer.jsp", request, response);
	}

	private void doMyUpdateRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		ABDto dto = new ABDto();
		dto.setBoardno(boardno);
		dto.setTitle(title);
		dto.setContent(content);
		int res = biz.update(dto);
		if (res > 0) {
			jsResponse("글 수정 완료", "mydetail?boardno=" + boardno, response);
		} else {
			jsResponse("글 수정 실패", "myupdate?boardno=" + boardno, response);
		}
	}

	private void doMyUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		ABDto dto = biz.selectOne(boardno);
		request.setAttribute("dto", dto);
		dispatch("myupdate.jsp", request, response);

	}

	private void doMyInsertRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		ABDto dto = new ABDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);

		int res = biz.insert(dto);
		if (res > 0) {
			jsResponse("글 작성 완료", "mylist", response);
		} else {
			jsResponse("글 작성 실패", "myinsert", response);
		}
	}

	private void doMyInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("myinsert.jsp");
	}

	private void doMyDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		ABDto dto = biz.selectOne(boardno);
		request.setAttribute("dto", dto);
		dispatch("mydetail.jsp", request, response);

	}

	// 글 전체
	private void doMyList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ABDto> list = biz.selectList();
		request.setAttribute("list", list);
		dispatch("mylist.jsp", request, response);
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

	public ABServlet() {

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
