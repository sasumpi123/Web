<%@page import="com.dao.MemberDao"%>
<%@page import="com.dto.MemberDto"%>
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
		String member_id = request.getParameter("member_id");
		String member_password = request.getParameter("member_password");
		MemberDto dto = new MemberDto();
		MemberDao dao = new MemberDao();
		dto.setMember_id(member_id);
		dto.setMember_password(member_password);

		if (dao.checkId(dto) > 0) {
	%>
	<script type="text/javascript">
		alert("로그인에 성공하였습니다");
		location.href = "mylist.jsp";
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("아이디/비밀번호를 확인해주세요");
		location.href = "login.jsp";
	</script>
	<%
		}
	%>

</body>
</html>