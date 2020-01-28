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
		MVCDto dto = (MVCDto) request.getAttribute("dto");
	%>
	<h1>수정</h1>

	<form action="controller.jsp" method="post">
		<input type="hidden" name="command" value="updateres">
		<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
		<table border="1">
			<tr>
				
				<th><%=dto.getWriter() %></th>
			</tr>
			<tr>
			<th><input type="text" name="title" value="<%=dto.getTitle() %>"> </th>
			</tr>
			<tr>
				<td><textarea rows="10" cols="60" name="content"><%=dto.getContent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정"> <input
					type="button" value="취소" onclick="location.href='controller.jsp?command=detail&seq=<%=dto.getSeq()%>'"></td>
			</tr>
		</table>
	</form>

</body>
</html>