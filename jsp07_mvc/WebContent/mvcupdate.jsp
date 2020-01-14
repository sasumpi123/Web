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

	<form action="mvc.do" method="post">
		<input type="hidden" name="command" value="updateres">
		<input type="hidden" name="myno" value="<%=dto.getMyno()%>">
		<table border="1">
			<tr>
				
				<th><%=dto.getMywriter() %></th>
			</tr>
			<tr>
			<th><input type="text" name="mytitle" value="<%=dto.getMytitle() %>"> </th>
			</tr>
			<tr>
				<td><textarea rows="10" cols="60" name="mycontent"><%=dto.getMycontent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정"> <input
					type="button" value="취소" onclick="location.href='mvc.do?command=detail&myno=<%=dto.getMyno()%>'"></td>
			</tr>
		</table>
	</form>

</body>
</html>