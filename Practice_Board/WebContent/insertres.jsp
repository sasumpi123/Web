<%@page import="com.dao.MyDao"%>
<%@page import="com.dto.MyDto"%>
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
	String board_title = request.getParameter("board_title");
	String board_content = request.getParameter("board_content");
	String board_writer = request.getParameter("board_writer");
	MyDto dto = new MyDto();
	MyDao dao = new MyDao();
	dto.setBoard_title(board_title);
	dto.setBoard_content(board_content);
	dto.setBoard_writer(board_writer);
	int res = dao.insert(dto);
	if (res > 0) {
%>
<script type="text/javascript">
	alert("글쓰기 성공");
	location.href = "list.jsp";
</script>

<%
	} else {
%>
<script type="text/javascript">
	alert("글쓰기 실패");
	location.href = "insert.jsp";
</script>


<%
	}
%>
</head>
<body>

</body>
</html>