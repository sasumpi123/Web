package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.login.biz.MemberBiz;
import com.login.biz.MemberBizImpl;
import com.login.dto.MemberDto;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		MemberBiz biz = new MemberBizImpl();
		HttpSession session = request.getSession();
		
		String command = request.getParameter("command");
		System.out.println("login : " + command);
		
		if(command.equals("login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDto dto = biz.login(id, pw);
			
			if(dto.getEnabled().equals("N")) {
				// 등록되어 있다면 Enabled가 N인지
				historyBack("탈퇴한 회원입니다", response);
			} else if(dto.getEnabled().equals("Y")) {
				session.setAttribute("id", dto.getId());
				session.setAttribute("role", dto.getRole());
				jsResponse("로그인 성공", "board.do?command=list&page=1", response);
			} else {
				// 그 외의 경우에는 전부 else로 다시 입력하게 하기
				historyBack("id와 pw를 다시 확인해 주세요", response);
			}
				
		} else if(command.equals("logout")) {
			session.invalidate();
			response.sendRedirect("board.do?command=list&page=1");
			
		} else if(command.equals("info")) {
			String id = request.getParameter("id");
			MemberDto dto = biz.selectOne(id);
			request.setAttribute("dto", dto);
			dispatch("myinfo.jsp", request, response);
			
		} else if(command.equals("update")) {
			String id = request.getParameter("id");
			MemberDto dto = biz.selectOne(id);
			request.setAttribute("dto", dto);
			dispatch("updateinfo.jsp",request, response);
			
		} else if(command.equals("updateres")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String addr = request.getParameter("addr");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			MemberDto dto = new MemberDto();
			dto.setId(id);
			dto.setPw(pw);
			dto.setName(name);
			dto.setAddr(addr);
			dto.setPhone(phone);
			dto.setEmail(email);
			
			int res = biz.updateUser(dto);
			if(res > 0) {
				jsResponse("회원정보 수정 성공", "login.do?command=info&id="+id, response);
			} else {
				historyBack("회원정보 수정 실패", response);
			}
			
		} else if(command.equals("delete")) {
			String id = request.getParameter("id");
			int res = biz.deleteUser(id);
			if(res > 0) {
				session.invalidate();
				jsResponse("탈퇴 성공", "board.do?command=list&page=1", response);
			} else {
				historyBack("탈퇴 실패", response);
			}
			
		} else if(command.equals("regist")) {
			response.sendRedirect("registform.jsp");
			
		} else if(command.equals("registres")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String addr = request.getParameter("addr");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			MemberDto dto = new MemberDto();
			dto.setId(id);
			dto.setPw(pw);
			dto.setName(name);
			dto.setAddr(addr);
			dto.setPhone(phone);
			dto.setEmail(email);
			
			int res = biz.insertUser(dto);
			if(res > 0) {
				jsResponse("회원가입 성공", "board.do?command=list&page=1", response);
			} else {
				historyBack("회원가입 실패", response);
			}
			
		} else if(command.equals("adminmenu")) {
			response.sendRedirect("adminmenu.jsp");
			
		} else if(command.equals("list")) {
			List<MemberDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("userlist.jsp", request, response);
			
		} else if(command.equals("enabled")) {
			List<MemberDto> list = biz.selectEnabled();
			request.setAttribute("list", list);
			dispatch("enabledlist.jsp", request, response);
			
		} else if(command.equals("updaterole")) {
			String id = request.getParameter("id");
			MemberDto dto = biz.selectOne(id);
			request.setAttribute("dto", dto);
			dispatch("updaterole.jsp", request, response);
		
		} else if(command.equals("updateroleres")) {
			String id = request.getParameter("id");
			String role = request.getParameter("role");
			int res = biz.updateRole(id, role);
			if(res > 0) {
				jsResponse("등급변경 성공", "login.do?command=enabled", response);
			} else {
				jsResponse("등급변경 실패", "login.do?command=enabled", response);
			}
			
		} else if(command.equals("roleform")) {
			String id = request.getParameter("id");
			MemberDto dto = biz.selectOne(id);
			request.setAttribute("dto", dto);
			dispatch("login.do?command=enabled", request, response);
			
			/* 미제사건
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("window.onload = function(){");
			out.println("open('login.do?command=updaterole&id="+id+"',\"\",\"width=200, height=200\")};");
			// out.println("location.href='login.do?command=enabled'");
			out.println("</script>");
			*/
			
		} else if(command.equals("idchk")) {
			String id = request.getParameter("id");
			MemberDto dto = biz.idChk(id);
			JSONObject obj = new JSONObject();
			if(dto == null || dto.getId().equals(null) || dto.getId() == null) {
				obj.put("idchk", "false");				
			} else if(dto.getId() == id || dto.getId().equals(id))  {
				obj.put("idchk", "true");
			}
			
			String res = obj.toJSONString();
			System.out.println("servlet에서 만들어짐" + res);
			
			PrintWriter out = response.getWriter();
			out.print(res);
			
		}
		
		
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("location.href='"+url+"'");
		out.println("</script>");
	}
	private void historyBack(String msg, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"');");
		out.println("history.back();");
		out.println("</script>");
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}

}
