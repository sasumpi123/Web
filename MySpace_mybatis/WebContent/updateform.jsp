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
<body>
<%@include file="./form/header.jsp" %>
<form action="board.do" method="post">
<input type="hidden" name="command" value="updateres"/>
<input type="hidden" name="seq" value="${dto.seq }"/>
	<table border="1" style="margin-left: auto; margin-right: auto">
		<tr>
			<th>번호</th>
			<td>${dto.seq }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${dto.title }"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea cols="60" rows="20" name="content">${dto.content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="수정"/>
				<input type="button" value="취소" onclick="location.href='board.do?command=detail&seq=${dto.seq}'"/>
			</td>
		</tr>
	</table>
</form>

<%@include file="./form/footer.jsp" %>

</body>
</html>