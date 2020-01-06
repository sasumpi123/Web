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
</head>
<body>

	<%
		String member_id = request.getParameter("member_id");
		String mytitle = request.getParameter("mytitle");
		String mycontent = request.getParameter("mycontent");
		MyDto dto = new MyDto();
		dto.setMyname(member_id);
		dto.setMytitle(mytitle);
		dto.setMycontent(mycontent);
		MyDao dao = new MyDao();
		int res = dao.insert(dto);
		if (res > 0) {
	%>
	<script type="text/javascript">
		alert("글 작성 성공");
		location.href = 'mylist.jsp?member_id=<%=member_id%>';
	</script>

	<%
		} else {
	%>
	<script type="text/javascript">
		alert("글 작성 실패");
		location.href = "myinsert.jsp?member_id=<%=member_id%>";
	</script>
	<%
		}
	%>


</body>
</html>