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
String member_id = request.getParameter("member_id");
%>

	<h1>글쓰기</h1>
	<form action="myinsertres.jsp?member_id=<%=member_id%>" method="post">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><%=member_id%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="mytitle"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="6" cols="60" name="mycontent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="작성"> 
				<input type="button" value="취소" onclick="location.href='mylist.jsp?member_id=<%=member_id%>'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>