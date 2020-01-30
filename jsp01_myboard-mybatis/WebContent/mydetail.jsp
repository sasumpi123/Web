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
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<body>


	<%
		
		
		int myno = Integer.parseInt(request.getParameter("myno")); // 글 번호 값을 받아와 저장
		
		
		MyDao dao = new MyDao();
		MyDto dto = dao.selectOne(myno); // 해당 번호에 맞는 글 데이터를 가져옴
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
			<input type="button" value="수정" onclick="location.href='myupdate.jsp?myno=<%=dto.getMyno()%>'" class="check">
			<input type="button" value="삭제" onclick="location.href='mydelete.jsp?myno=<%=dto.getMyno()%>'" class="check">
			<input type="button" value="목록" onclick="location.href='mylist.jsp'">
			<input type="button" value="답글달기" onclick="location.href='reply.jsp?myno=<%=dto.getMyno()%>'">
			</td>
		</tr>
	</table>




</body>
</html>