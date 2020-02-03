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
<form action="login.do" method="post">
<input type="hidden" name="command" value="updateres"/>
<input type="hidden" name="id" value="${dto.id }"/>
	<table border="1" style="margin-left: auto; margin-right: auto;">
		<tr>
			<th>아이디</th>
			<td>${dto.id }</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pw" value="${dto.pw }"/></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" value="${dto.name }"/></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="addr" value="${dto.addr }"/></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="phone" value="${dto.phone }"/></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email" value="${dto.email }"/></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="수정"/>
				<input type="button" value="취소" onclick="history.back();"/>
				<input type="button" value="메인" onclick="location.href='board.do?command=list'"/>
			</td>
		</tr>
	</table>
</form>
<%@include file="./form/footer.jsp" %>
</body>
</html>