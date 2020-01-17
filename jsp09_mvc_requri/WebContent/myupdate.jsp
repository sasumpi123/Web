<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<form action="myupdateres" method="post">
		<input type="hidden" name="myno" value="${dto.myno }">
		<h1>글 수정</h1>
		<table border="1">
			<tr>
				<th>작성자</th>
				<td>${dto.mywriter }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="mytitle" value="${dto.mytitle }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="6" cols="60" name="mycontent">${dto.mycontent }</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정"> <input
					type="button" value="취소"
					onclick="location.href='mydetail?myno=${dto.myno}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>