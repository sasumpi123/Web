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
	String pw = "";
	for (int i = 0; i < dto.getMypw().length(); i++) {
		pw += '*';
	}
%>
<body>

	<h1>내정보 조회</h1>

	<table border="1">
		<tr>
			<th>아이디</th>
			<th><%=dto.getMyid()%></th>
		</tr>
		<tr>
			<th>비밀번호</th>
			<th><%=pw%></th>
		</tr>
		<tr>
			<th>주소</th>
			<th><%=dto.getMyaddr()%></th>
		</tr>
		<tr>
			<th>전화번호</th>
			<th><%=dto.getMyphone()%></th>
		</tr>
		<tr>
			<th>이메일</th>
			<th><%=dto.getMyemail()%></th>
		</tr>
		<tr>
			<th>등급</th>
			<th><%=dto.getMyrole()%></th>
		</tr>
		<tr>
			<td colspan="2">
				<button
					onclick="location.href='logincontroller.jsp?command=userinfoupdate'">회원정보변경</button>
				<button onclick="location.href='usermain.jsp'">메인으로</button>
			</td>
		</tr>

	</table>

</body>
</html>