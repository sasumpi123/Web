<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html, charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>회원가입</h1>
	<form action="signinres.jsp" method="post">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="member_id"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="member_password"></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="가입"> 
				<input type="button" value="취소" onclick="location.href='login.jsp'">
				</td>
			</tr>
		</table>
	</form>



</body>
</html>