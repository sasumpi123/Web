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
		String id = (String) session.getAttribute("id"); // 세션에서 id값 받아옴
		int myno = Integer.parseInt(request.getParameter("myno")); // 번호값 받아옴
		MyDao dao = new MyDao();
		MyDto dto = new MyDto();
		dto = dao.selectOne(myno);
		if ((id).compareTo(dto.getMyname()) != 0) { // 현재 접속해있는 아이디와 작성된 글의 작성자 아이디가 일치하지 않다면
													// 글 삭제 권한 없음
	%>
	<script type="text/javascript">
			alert("권한없음");
			location.href='mydetail.jsp?myno=<%=myno%>';
			
			</script>
	<%
		} else {
	%>


	<%
		int res = dao.delete(myno);
			if (res > 0) {
	%>
	<script type="text/javascript">
		alert("글 삭제 성공");
		location.href = 'mylist.jsp';
	</script>

	<%
		} else {
	%>
	<script type="text/javascript">
		alert("글 삭제 실패");
		location.href = 'mydetail.jsp?myno<%=myno%>';
	</script>
	<%
		}
		}
	%>



</body>
</html>