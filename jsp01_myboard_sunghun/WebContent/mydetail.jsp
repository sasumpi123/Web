<%@page import="com.dto.MyDto"%>
<%@page import="com.dao.MyDao"%>
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
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyDao dao = new MyDao();
		MyDto dto = dao.selectOne(myno);
	%>

	<h1>Detail</h1>
	<table border="1">
		<tr>
			<th>이름</th>
			<td><%=dto.getMyname()%></td>
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
			
			<td colspan="1">
			<input type="button" value="수정" onclick="location.href='myupdate.jsp?myno=<%=dto.getMyno()%>&member_id=<%=member_id%>'">
			<input type="button" value="삭제" onclick="location.href='mydelete.jsp?myno=<%=dto.getMyno()%>&member_id=<%=member_id%>'">
			<input type="button" value="목록" onclick="location.href='mylist.jsp?member_id=<%=member_id%>'">
			</td>
		</tr>
	</table>
	
	


</body>
</html>