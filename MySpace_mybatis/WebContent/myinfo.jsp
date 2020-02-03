<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="./form/header.jsp" %>
	<table border="1" style="margin-left: auto; margin-right: auto;">
		<tr>
			<th>아이디</th>
			<td>${dto.id }</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" value="${dto.pw }"/></td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${dto.name }</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${dto.addr }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${dto.phone }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${dto.email }</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정" onclick="location.href='login.do?command=update&id=${dto.id}'"/>
				<input type="button" value="탈퇴" onclick="location.href='login.do?command=delete&id=${dto.id}'"/>
				<input type="button" value="메인" onclick="location.href='board.do?command=list&page=1'"/>
			</td>
		</tr>
	</table>
<%@include file="./form/footer.jsp" %>
</body>
</html>