<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

	<table border="1">
		<tr>
			<th>글번호</th>
			<th>그룹번호</th>
			<th>그룹순서</th>
			<th>제목공백</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>답글달기</th>
		</tr>
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<td colspan="8">-------작성된 글이 존재하지 않습니다--------</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.boardno }</td>
						<td>${dto.groupno }</td>
						<td>${dto.groupseq }</a></td>
						<td>${dto.titletab}</td>
						<td>
						<c:forEach begin="1" end="${dto.titletab}">
							&nbsp;&nbsp;
						</c:forEach>
						<c:if test=""></c:if>
						<a href="mydetail?boardno=${dto.boardno}">${dto.title}</a>
						</td>
						<td>${dto.content}</td>
						<td>${dto.writer}</td>
						<td>${dto.regdate}</td>
						<td><input type="button" value="답글" onclick="location.href='insertanswer?boardno=${dto.boardno}'"></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="9"><input type="button" value="글쓰기" onclick="location.href='myinsert'">
			</td>
		</tr>
	</table>
</body>
</html>