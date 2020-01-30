<%@page import="com.my.dao.MyDao"%>
<%@page import="com.my.dto.MyDto"%>
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

<%
	String board_number = request.getParameter("board_number");
	MyDto dto = new MyDto();
	MyDao dao = new MyDao();
	int res = dao.delete(board_number);
	if (res > 0) {
%>
<script type="text/javascript">
alert("삭제성공");
location.href="list.jsp";
</script>
<%
	} else {
%>
<script type="text/javascript">
		alert("삭제실패");
		location.href="mydetail.jsp?board_number=<%=board_number%>"
</script>
<%
	}
%>
</head>
<body>

</body>
</html>