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
			MVCDto dto = (MVCDto) request.getAttribute("dto");
	%>

	<h1></h1>

	<form action="controller.jsp" method="post">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><%=dto.getWriter()%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=dto.getTitle()%></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" readonly="readonly">
			<%=dto.getContent()%>
			</textarea></td>
			</tr>
			<tr>
				<td colspan="1"><input type="button"
					onclick="location.href='controller.jsp?command=list'" value="목록">
					<input type="button"
					onclick="location.href='controller.jsp?command=update&seq=<%=dto.getSeq()%>'"
					value="수정"> <input type="button"
					onclick="location.href='controller.jsp?command=delete&seq=<%=dto.getSeq()%>'"
					value="삭제"></td>
			</tr>
		</table>
	</form>


</body>
</html>