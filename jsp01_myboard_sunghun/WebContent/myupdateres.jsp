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
		int myno = Integer.parseInt(request.getParameter("myno"));
		String mytitle = request.getParameter("mytitle");
		String mycontent = request.getParameter("mycontent");
		MyDto dto = new MyDto();
		dto.setMyno(myno);
		dto.setMytitle(mytitle);
		dto.setMycontent(mycontent);
		MyDao dao = new MyDao();
		int res = dao.update(dto);
		if (res > 0) {
	%>
	<script type="text/javascript">
		alert("글 수정 성공");
		location.href = "mydetail.jsp?myno=<%=dto.getMyno()%>&member_id=<%=member_id%>";
	</script>

	<%
		} else {
	%>
	<script type="text/javascript">
		alert("글 수정 실패");
		location.href = "mylist.jsp?member_id=<%=member_id%>";
	</script>
	<%
		}
	%>

</body>
</html>