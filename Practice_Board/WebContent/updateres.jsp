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
	String board_title = request.getParameter("board_title");
	String board_content = request.getParameter("board_content");
	int board_number = Integer.parseInt(request.getParameter("board_number"));
	MyDto dto = new MyDto();
	MyDao dao = new MyDao();

	dto.setBoard_title(board_title);
	dto.setBoard_content(board_content);
	dto.setBoard_number(board_number);
	int res = dao.update(dto);

	if (res > 0) {
%>
<script type="text/javascript">
		alert("수정 성공");
		location.href="detail.jsp?board_number=<%=board_number%>";
</script>

<%
	} else {
%>
<script type="text/javascript">

alert("수정 실패");
location.href="update.jsp?board_number=<%=board_number%>";
</script>


<%
	}
%>
</head>
<body>

</body>
</html>