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
</head>
<%
	MemberDto dto = (MemberDto)request.getAttribute("dto");
%>
<body>

<form action="login.do" method="post">
		<input type="hidden" name="command" value="updateroleres"/>
		<input type="hidden" name="id" value="${dto.id }"/>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<td>${dto.id }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${dto.name }</td>
			</tr>
			<tr>
				<th>등급</th>
				<td>
					<select name="role">
						<option value="USER" <%=dto.getRole().equals("USER")?"selected":"" %>>일반회원</option>
						<option value="MANAGER" <%=dto.getRole().equals("MANAGER")?"selected":"" %>>우수회원</option>
						<option value="ADMIN" <%=dto.getRole().equals("ADMIN")?"selected":"" %>>관리자</option>
						<!-- select -> option 태그에 selected라는 속성을 주면 처음 접속했을 때 해당 속성이 있는 태그가 선택되어 나온다. -->
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="변경"/>
					<input type="button" value="취소" value="location.href='history.back()'"/>					
				</td>
			</tr>
		</table>
	</form>

</body>
</html>