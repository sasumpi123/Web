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
	<form action="insertanswerres" method="post">
		<input type="hidden" name="boardno" value="${dto.boardno }">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" placeholder="아이디를 입력해주세요"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" readonly="readonly" value="${dto.title }"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea rows="6" cols="60" name="content"
						placeholder="내용을 작성해주세요"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="작성"> <input
					type="button" value="취소" onclick="location.href='mylist'">
			</tr>
		</table>
	</form>
</body>
</html>