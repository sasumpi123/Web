<%@page import="com.mvc.dto.MVCDto"%>
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
		MVCDto dto = new MVCDto();
	%>
	<h1>수정</h1>

	<form action="mvc.do" method="post">
		<input type="hidden" name="command" value="insertres"> <input
			type="hidden" name="mywriter" value="관리자">
		<table border="1">
			<tr>
				<th>제목 :<input type="text" name="mytitle"
					value=""></th>
			</tr>
			<tr>
				<td> <textarea rows="10" cols="60" name="mycontent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="작성"> <input
					type="button" value="취소"
					onclick="location.href='mvc.do?command=list'"></td>
			</tr>
		</table>
	</form>

</body>
</html>