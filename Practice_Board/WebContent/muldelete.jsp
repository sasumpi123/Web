<%@page import="com.my.dao.MyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html, charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%

String[] no = request.getParameterValues("chk");
MyDao dao = new MyDao();
int[] res = dao.muldelete(no);

%>

</head>
<body>
<script type="text/javascript">
location.href="list.jsp";
</script>
</body>
</html>