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

	

	<h1>상세 글 보기</h1>
	<table border="1">
		<tr>
			<th>작성자</th>
			<td>${dto.mywriter }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.mytitle }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="6" cols="60" readonly="readonly">${dto.mycontent }</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='myupdate?myno=${dto.myno}'">
				<input type="button" value="삭제" onclick="location.href='mydelete?myno=${dto.myno}'">
				<input type="button" value="목록" onclick="location.href='con.do?command=list'">
			</td>
		</tr>
	</table>
</body>
</html>