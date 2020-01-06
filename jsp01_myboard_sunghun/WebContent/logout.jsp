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
		session.invalidate(); // 세선졍보 제거(로그아웃)
	%>
	<script>
		alert("로그아웃 되었습니다.");
		location.href = "login.jsp"; //페이지로 이동
	</script>


</body>
</html>