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

<%
	String[] chk = request.getParameterValues("chk");				// values로 chk라는 이름을 가진 값들을 chk[]에 담는다
	MyDao dao = new MyDao();
	int res = dao.selectDelete(chk);
%>
</head>
<body>

	<%
		if(res>0){
	%>
	<script type="text/javascript">
		alert=("삭제성공");
	</script>
	<%
	}else{
	%>
	<script type="text/javascript">
		alert=("삭제실패");
	</script>
	<%	
		}
	%>
	<script type="text/javascript">
		location.href = "mylist.jsp"
	</script>

</body>
</html>