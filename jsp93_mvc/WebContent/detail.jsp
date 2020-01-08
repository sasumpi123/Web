<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="java.util.List"%>
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
		// request, response 모두 Object형태
		// 따라서 list 형태로 casting 필요
		MVCDto dto = new MVCDto();
		dto = (MVCDto) request.getAttribute("dto");
	%>

	<h1></h1>

	<form action="controller.jsp" method="post">
		<table border="1">
			<col width="30">
			<col width="50">
			<col width="100">
			<col width="300">
			<col width="100">
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성일</th>
			<tr>
				<td><%=dto.getSeq()%></td>
				<td><%=dto.getWriter()%></td>
				<td><%=dto.getTitle()%></td>
				<td><%=dto.getContent()%></td>
				<td><%=dto.getRegdate()%></td>
			</tr>
		</table>
		<input type="button" onclick="location.href='controller.jsp?command=list'" value="목록">
		<input type="button" onclick="location.href='controller.jsp?command=update&seq=<%=dto.getSeq() %>'" value="수정">
		<input type="button" onclick="location.href='controller.jsp?command=delete&seq=<%=dto.getSeq() %>'" value="삭제">
	</form>


</body>
</html>