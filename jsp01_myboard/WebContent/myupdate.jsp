<%@page import="com.my.dto.MyDto"%>
<%@page import="com.my.dao.MyDao"%>
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
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyDao dao = new MyDao();
		MyDto dto = new MyDto();
	%>
	<h1>수정</h1>
	<form action="myupdateres.jsp" method="post">
		<table border="1">
			<tr>
			<th>이름</th>
			<td><%=dto.getMyname()%></td>
		</tr>
		<tr>
			<th>제목</th>
			<input type="text"><%=dto.getMytitle()%></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60">
			<%=dto.getMycontent()%>
			</textarea></td>
		</tr>
		<tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정"> <input
					type="button" value="취소"
					onclick='location.href = "mydetail.jsp?myno=<%=myno%>";'></td>
			</tr>
		</table>
	</form>

</body>
</html>