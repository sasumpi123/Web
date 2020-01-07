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

<%
	String[] chk = request.getParameterValues("chk");				// values로 chk라는 이름을 가진 값들을 chk[]에 담는다
	MyDao dao = new MyDao();
	int[] res = dao.selectDelete(chk);
%>
</head>
<body>

	<%
		for (int i = 0; i < res.length; i++) {
			if (res[i] != -2) {
	%>
	<script type="text/javascript">
	alert=("<%=res[i]%>번째글 삭제실패");
	</script>
	<%
		}
		}
	%>
	<script type="text/javascript">
		location.href = "mylist.jsp"
	</script>

</body>
</html>