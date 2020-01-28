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

	<h1>상세 글 보기</h1>
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><jsp:getProperty property="mywriter" name="dto" /></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><jsp:getProperty property="mytitle" name="dto" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="6" cols="60" readonly="readonly"><jsp:getProperty
						property="mycontent" name="dto" /></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='con.do?command=update&myno=${dto.myno}'">
				<input type="button" value="삭제" onclick="location.href='con.do?command=delete&myno=${dto.myno}'">
				<input type="button" value="목록" onclick="location.href='con.do?command=list'">
			</td>
		</tr>
	</table>
</body>
</html>