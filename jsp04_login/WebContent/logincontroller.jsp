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
				session.setMaxInactiveInterval(10 * 60000);
				System.out.println(dto.getMyenabled());
				if (dto.getMyenabled().equals("N")) {
					response.sendRedirect("logindeny.jsp");
				} else if (dto.getMyrole().equals("USER") || dto.getMyrole().equals("MANAGER")) {
					response.sendRedirect("usermain.jsp");
				} else if (dto.getMyrole().equals("ADMIN")) {
					response.sendRedirect("adminmain.jsp");
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
		location.href="controller.jsp?command=updateroleform&myno=<%=myno%>
		";
	</script>

	<%
		}
		} else if (command.equals("registform")) {
			response.sendRedirect("registform.jsp");
		} else if (command.equals("idchk")) {
			String myid = request.getParameter("id");
			MemberDto dto = biz.idChk(myid);
			System.out.println(dto.getMyid());
			boolean idnotused = true;
			if (dto.getMyid() != null) {
				idnotused = false;
			}
			response.sendRedirect("idchk.jsp?idnotused=" + idnotused);
		} else if (command.equals("registres")) {
			MemberDto dto = new MemberDto();
			String myid = request.getParameter("myid");
			String mypw = request.getParameter("mypw");
			String myname = request.getParameter("myname");
			String myaddr = request.getParameter("myaddr");
			String myphone = request.getParameter("myphone");
			String myemail = request.getParameter("myemail");
			System.out.println(myid);
			dto.setMyid(myid);
			dto.setMypw(mypw);
			dto.setMyname(myname);
			dto.setMyaddr(myaddr);
			dto.setMyphone(myphone);
			dto.setMyemail(myemail);
			System.out.println(dto.getMyid());
			int res = biz.insertUser(dto);
			if (res > 0) {
	%>
	<script type="text/javascript">
		alert("회원가입을 성공하였습니다");
		location.href = "index.jsp";
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("회원가입을 실패하였습니다.");
		location.href = "logincontroller.jsp?command=registform";
	</script>
	<%
		}
		} else if (command.equals("userinfo")) {
			pageContext.forward("userinfo.jsp");
		} else if (command.equals("userinfoupdate")) {
			pageContext.forward("userinfoupdate.jsp");
		} else if (command.equals("userinfoupdateres")) {
			MemberDto dto = new MemberDto();
			int myno = Integer.parseInt(request.getParameter("myno"));
			String mypw = request.getParameter("mypw");
			String myaddr = request.getParameter("myaddr");
			String myphone = request.getParameter("myphone");
			String myemail = request.getParameter("myemail");
			dto.setMyno(myno);
			dto.setMypw(mypw);
			dto.setMyaddr(myaddr);
			dto.setMyphone(myphone);
			dto.setMyemail(myemail);

			int res = biz.updateUser(dto);
			if (res > 0) {
				dto = biz.selectUser(dto.getMyno());
				session.setAttribute("dto", dto);
	%>
	<script type="text/javascript">
		alert("회원정보 수정성공");
		location.href = "userinfo.jsp";
	</script>

	<%
		} else {
	%>
	<script type="text/javascript">
		alert("회원정보 수정실패 주소,전화번호,이메일을 다시한번 확인해주세요");
		location.href = "userinfoupdate.jsp";
	</script>
	<%
		}
		} else if (command.equals("userdelete")) {
			MemberDto dto = (MemberDto) session.getAttribute("dto");
			int res = biz.deleteUser(dto.getMyno());
			if (res > 0) {
	%>
	<script type="text/javascript">
				alert("회원탈퇴 성공");
				location.href="index.jsp"
				</script>
	<%
		} else {
	%>
	<script type="text/javascript">
				alert("회원탈퇴 실패");
				location.href="userinfoupdate.jsp";
				</script>
	<%
		}
		}
	%>

	<h1 style="color: red;">잘못왔따..</h1>

</body>
</html>