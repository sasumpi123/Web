package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.dto.ReplyDto;
import com.board.biz.BoardBiz;
import com.board.biz.BoardBizImpl;
import com.board.dto.BoardDto;
import com.paging.biz.PagingBiz;
import com.paging.biz.PagingBizImpl;
import com.paging.dto.PagingDto;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		BoardBiz biz = new BoardBizImpl();
		PagingBiz pagingBiz = new PagingBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("board : " + command);
		
		if(command.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page"));
			PagingDto paging = new PagingDto();
			paging.setPage(page);
			paging.setRows(10);
			paging.setPagescale(3);
			paging.setTotalpage(pagingBiz.totalPage(paging.getRows()));
			List<ReplyDto> list = biz.selectList(paging);
			request.setAttribute("list", list);
			request.setAttribute("Paging", paging);
			dispatch("boardlist.jsp",request,response);
			
		} else if(command.equals("write")) {
			response.sendRedirect("writeform.jsp");
			
		} else if(command.equals("writeres")){
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			System.out.println(writer);
			System.out.println(title);
			System.out.println(content	);
			
			BoardDto dto = new BoardDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.insert(dto);
			if(res > 0) {
				jsResponse("글 작성 성공", "board.do?command=list&page=1", response);
			} else {
				historyBack("글 작성 실패", response);
			}
			
		} else if(command.equals("detail")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			BoardDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("detail.jsp", request, response);
			
		} else if(command.equals("update")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			BoardDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("updateform.jsp",request, response);
			
		} else if(command.equals("updateres")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BoardDto dto = new BoardDto();
			dto.setSeq(seq);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.update(dto);
			if(res > 0) {
				jsResponse("수정 성공", "board.do?command=detail&seq="+seq, response);
			} else {
				historyBack("수정 실패", response);
			}
			
		} else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			int res = biz.delete(seq);
			if(res > 0) {
				jsResponse("삭제 성공", "board.do?command=list&page=1", response);
			} else {
				historyBack("삭제 실패",response);
			}
		}
		
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='"+url+"';");
		out.println("</script>");
	}
	
	private void historyBack(String msg, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("history.back();");
		out.println("</script>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}

}
