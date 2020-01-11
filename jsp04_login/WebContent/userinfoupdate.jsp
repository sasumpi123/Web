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

<%
	MemberDto dto = new MemberDto();
	dto = (MemberDto) session.getAttribute("dto");
%>
<body>

	<h1>회원정보 변경</h1>
	<form action="logincontroller.jsp" method="post">
	<input type="hidden" name="command" value="userinfoupdateres">
	<input type="hidden" name="myno" value="<%=dto.getMyno()%>">

		<table border="1">
			<tr>
				<th>아이디</th>
				<th><%=dto.getMyid()%></th>
			</tr>
			<tr>
				<th>비밀번호</th>
				<th><input type="text" name="mypw" value="<%=dto.getMypw()%>">
				</th>
			</tr>
			<tr>
				<th>주소</th>
				<th><input type="text" name="myaddr"
					value="<%=dto.getMyaddr()%>"></th>
			</tr>
			<tr>
				<th>전화번호</th>
				<th><input type="text" name="myphone"
					value="<%=dto.getMyphone()%>"></th>
			</tr>
			<tr>
				<th>이메일</th>
				<th><input type="text" name="myemail"
					value="<%=dto.getMyemail()%>"></th>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="변경">
				<input type="button" value="취소" onclick="location.href='logincontroller.jsp?command=userinfo'"></td>
			</tr>
		</table>
	</form>

</body>
</html>