<%@page import="com.mvc.dto.MVCDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html, charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	MVCDto dto = (MVCDto)request.getAttribute("dto");
%>
<body>


<form action="mvc.do" method="post">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><%=dto.getMywriter()%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=dto.getMytitle()%></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" readonly="readonly">
			<%=dto.getMycontent()%>
			</textarea></td>
			</tr>
			<tr>
				<td colspan="1"><input type="button"
					onclick="location.href='mvc.do?command=list'" value="목록">
					<input type="button"
					onclick="location.href='mvc.do?command=update&myno=<%=dto.getMyno() %>'"
					value="수정"> <input type="button"
					onclick="location.href='mvc.do?command=delete&myno=<%=dto.getMyno()%>'"
					value="삭제"></td>
			</tr>
		</table>
	</form>

</body>
</html>