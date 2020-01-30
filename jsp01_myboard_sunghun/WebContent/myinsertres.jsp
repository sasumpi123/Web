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
</head>
<body>

	<%
		String id = (String) session.getAttribute("id");		// 세션에서 id 받아와 저장
		String mytitle = request.getParameter("mytitle"); 		// 이전 페이지에서 제목 받아와 저장
		String mycontent = request.getParameter("mycontent");	// 이전 페이지에서 내용 받아와 저장
		MyDto dto = new MyDto();								// MyDto 선언
		dto.setMyname(id);										
		dto.setMytitle(mytitle);
		dto.setMycontent(mycontent);							
																// 값들 설정해줌
		MyDao dao = new MyDao();								// MyDao 선언
		int res = dao.insert(dto);								// 결과 받아옴
		if (res > 0) {
	%>
	<script type="text/javascript">
		alert("글 작성 성공");
		location.href = 'mylist.jsp';
	</script>

	<%
		} else {
	%>
	<script type="text/javascript">
		alert("글 작성 실패");
		location.href = "myinsert.jsp";
	</script>
	<%
		}
	%>


</body>
</html>