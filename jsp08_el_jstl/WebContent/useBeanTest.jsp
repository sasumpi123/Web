<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html, charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Score lee = new Score(); 과 같다-->
<jsp:useBean id="lee" class="com.test.dto.Score" scope="session"></jsp:useBean>

<!-- lee.setName("이순신") 과 같다-->
<jsp:setProperty property="name" name="lee" value="이순신"/>

<!-- lee.getName()과 같다 -->
<jsp:getProperty property="name" name="lee"/>

<a href="res.jsp">result</a>
</body>
</html>