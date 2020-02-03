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
	<jsp:useBean id="dto" class="com.board.dto.BoardDto" scope="request"></jsp:useBean>

	<table border="1" style="margin-left: auto; margin-right: auto">
		<tr>
			<th>번호</th>
			<td><jsp:getProperty property="seq" name="dto"/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><jsp:getProperty property="writer" name="dto"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><jsp:getProperty property="title" name="dto"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea cols="60" rows="20" readonly="readonly"><jsp:getProperty property="content" name="dto"/></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정" onclick="location.href='board.do?command=update&seq=<jsp:getProperty property="seq" name="dto"/>'"/>
				<input type="button" value="삭제" onclick="location.href='board.do?command=delete&seq=<jsp:getProperty property="seq" name="dto"/>'"/>
				<input type="button" value="목록" onclick="location.href='board.do?command=list&page=1'"/>
			</td>
		</tr>
	</table>

<%@include file="./form/footer.jsp" %>
</body>
</html>