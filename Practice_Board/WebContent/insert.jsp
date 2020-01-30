<%@page import="com.my.dao.MyDao"%>
<%@page import="com.my.dto.MyDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html, charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="src/listcss.css">
</head>
<body>
<%

%>

<section>
<form action="insertres.jsp" method="get">
		<h1>Board</h1>
		<div class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th>작성자<input type="text" value="" name="board_writer"></th>
						<th>제목 <input type="text" value="" name="board_title"></th>

					</tr>
				</thead>
			</table>
		</div>
		<div class="tbl-content">
			<table cellpadding="0" cellspacing="0" border="0">
				
				<tbody>
					<tr>
						<td><textarea rows="10" cols="251" name="board_content"></textarea>
							
						</td>
					</tr>
				</tbody>
			</table>
			
		</div>
		<button onclick="location.href='insertres.jsp'">글쓰기</button>
		</form>
		<button onclick="location.href='list.jsp'">취소</button>
	</section>


</body>
</html>