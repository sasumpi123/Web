<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
	function updaterole(){
		var id = document.getElementById("id");
		open("login.do?command=updaterole&id="+id.value,"","width=200, height=200");
	}	

</script>
</head>
<body>
<%@include file="./form/header.jsp" %>
	<h1 align="center">회원정보 조회</h1>
	
	<table border="1" style="margin-left: auto; margin-right: auto;">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입여부</th>
			<th>등급</th>
			<th>회원관리</th>
		</tr>
	<c:choose>
		<c:when test="${empty list }">
			<tr>
				<td>- - - - - - - - - - 등록된 회원이 없습니다 - - - - - - - - - -</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list }" var="dto">
				<tr align="center">
					<td><a href="login.do?command=info&id=${dto.id }">${dto.id }</a></td>
					<td>${dto.pw }</td>
					<td>${dto.name }</td>
					<td>${dto.addr }</td>
					<td>${dto.phone }</td>
					<td>${dto.email }</td>
					<td>
						<c:if test="${dto.enabled eq 'Y'}">
							<c:out value="가입"></c:out>
						</c:if>
						<c:if test="${dto.enabled eq 'N' }">
							<c:out value="탈퇴"></c:out>
						</c:if>
					</td>
					<td>${dto.role }</td>
					<td><input type="button" value="등급변경" onclick="location.href='login.do?command=updaterole&id=${dto.id}'"/></td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>

		<tr>
			<td colspan="9" align="right">
				<button onclick="location.href='adminmenu.jsp'">메인으로</button>
			</td>
		</tr>
	</table>
<%@include file="./form/footer.jsp" %>
</body>
</html>