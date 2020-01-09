<%@page import="java.util.List"%>
<%@page import="com.login.dto.MemberDto"%>
<%@page import="com.login.biz.MemberBizImpl"%>
<%@page import="com.login.biz.MemberBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html, charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String command = request.getParameter("command");
		System.out.println("[" + command + "]"); // 현재 command 상태표시

		MemberBiz biz = new MemberBizImpl();

		if (command.equals("login")) {
			String myid = request.getParameter("id");
			String mypw = request.getParameter("pw");

			MemberDto dto = biz.login(myid, mypw);

			if (dto != null) {
				// session : 만료되기 전까지 어플리케이션 전체에서 사용 가능
				session.setAttribute("dto", dto);
				// session.setMaxInactiveInterval(second) : 해당 초만큼 활동이 없으면 session을 종료한다(invalidate)
				// default : 30분 음수이면 무제한
				session.setMaxInactiveInterval(10 * 60);

				if (dto.getMyrole().equals("ADMIN")) {
					response.sendRedirect("adminmain.jsp");
				}else if(dto.getMyrole().equals("USER")){
					response.sendRedirect("usermain.jsp");
				}
			} else {
	%>
	<script type="text/javascript">
		alert("id와 pw를 다시한번 확인해주세요!");
		location.href = "index.jsp";
	</script>
	<%
		}
		} else if (command.equals("logout")) {
			// invalidate() : session 만료
			session.invalidate();
			response.sendRedirect("index.jsp");

		} else if (command.equals("selectlist")) {
			List<MemberDto> list = biz.selectList();
			request.setAttribute("list", list);
			pageContext.forward("userselectlist.jsp");

		} else if (command.equals("userselectenabled")) {
			List<MemberDto> list = biz.selectEnabled();
			request.setAttribute("list", list);
			pageContext.forward("userselectenabled.jsp");

		} else if (command.equals("updateroleform")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MemberDto dto = biz.selectUser(myno);
			request.setAttribute("select", dto);
			pageContext.forward("updaterole.jsp");

		} else if (command.equals("updateroleres")) {
			String role = request.getParameter("role");
			int myno = Integer.parseInt(request.getParameter("myno"));
			int res = biz.updateRole(myno, role);

			if (res > 0) {
	%>
	<script type="text/javascript">
		alert("등급변경 완료");
		location.href = "adminmain.jsp";
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("등급변경 실패");
		location.href="controller.jsp?command=updateroleform&myno=<%=myno%>";

	</script>

	<%
		}
		}
	%>

	<h1 style="color: red;">잘못왔따..</h1>

</body>
</html>