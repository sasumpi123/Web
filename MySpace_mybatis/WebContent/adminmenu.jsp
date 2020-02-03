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

	<h1 align="center">회원관리 메뉴</h1>

	<table border="1" style="margin-left: auto; margin-right: auto;">
		<tr>
			<th>정보조회</th>
			<td>
				<input type="button" value="전체조회" onclick="location.href='login.do?command=list'"/>
				<input type="button" value="가입조회" onclick="location.href='login.do?command=enabled'"/>
			</td>
		</tr>
		<tr>
			<th>회원관리</th>
			<td>
				<input type="button" value="등급변경" onclick="location.href='login.do?command=updaterole'"/>
				<input type="button" value="메인으로" onclick="location.href='board.do?command=list'"/>
			</td>
		</tr>
	</table>
	
</body>
</html>