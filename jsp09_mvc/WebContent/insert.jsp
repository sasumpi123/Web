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
	<jsp:useBean id="dto" class="com.mvc.mydto.MyDto" scope="request"></jsp:useBean>
	<form action="con.do" method="post">
	<input type="hidden" name="command" value="insertres">
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="mytitle" placeholder="제목을 작성해주세요"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea rows="6" cols="60" name="mycontent"
						placeholder="내용을 작성해주세요"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="작성"> <input
					type="button" value="취소"
					onclick="location.href='con.do?command=list'">
			</tr>
		</table>
	</form>
</body>
</html>