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
		String id = (String) session.getAttribute("id"); // 세션에서 id 값을 받아와 사용
		int myno = Integer.parseInt(request.getParameter("myno")); // 번호값 받아옴
	%>

	<h1>답글달기</h1>
	<form action="replyres.jsp" method="post">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><%=id%></td>
			</tr>
			<tr>
				<th>답글 제목</th>
				<td><input type="text" name="mytitle"></td>
			</tr>
			<tr>
				<th>답글 내용</th>
				<td><textarea rows="6" cols="60" name="mycontent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="작성"> <input
					type="button" value="취소"
					onclick="location.href='mydetail.jsp?myno=<%=myno%>'"></td>
			</tr>
		</table>
	</form>


</body>
</html>