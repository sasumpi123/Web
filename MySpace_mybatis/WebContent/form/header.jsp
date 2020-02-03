<%@page import="com.login.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{
		margin: 0px;
		padding: 0px;	
	}
	
	header{
		background-color: silver;
		height: 50px;
	}
	footer{
		background-color: silver;
		height: 50px;
		text-align: center;
		
	}
	.login{
		float: right;
	}
	
	form{
		margin: 5px 10px 0px 0px;
	}

</style>
</head>
<body>
	<header>
<%
	if(session.getAttribute("id") == null || session.getAttribute("id").equals(null)) {	
%>
		<form action="login.do" method="post" class="login">
		<input type="hidden" name="command" value="login"/>
			<input type="text" name="id" placeholder="아이디"/>
			<input type="password" name="pw" placeholder="비밀번호"/>
			<input type="submit" value="로그인"/>
			<input type="button" value="회원가입" onclick="location.href='login.do?command=regist'"/>
		</form>
<%
	} else {
%>
		<form action="" method="post" class="login">
		<span><%=session.getAttribute("id")%>님 | (<%=session.getAttribute("role")%> 등급) | 환영합니다</span>
		<input type="button" value="내 정보" onclick="location.href='login.do?command=info&id=<%=session.getAttribute("id")%>'"/>
<%
		if(session.getAttribute("role").equals("ADMIN") || session.getAttribute("role") == "ADMIN"){
%>
		<input type="button" value="회원관리"  onclick="location.href='login.do?command=adminmenu'"/>
<%
		}
%>
		<input type="button" value="로그아웃"  onclick="location.href='login.do?command=logout'"/>
<%
	}
%>
		</form>
	</header>
</body>
</html>