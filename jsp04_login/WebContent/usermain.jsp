<%@page import="com.login.dto.MemberDto"%>
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
		MemberDto dto = (MemberDto) session.getAttribute("dto");
	%>

	<h1><%=dto.getMyname()%>님 환영합니다.
	</h1>
	<a href="logincontroller.jsp?command=myinfo">내정보</a>


</body>
</html>