<%@page import="com.dao.MyDao"%>
<%@page import="com.dto.MyDto"%>
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
	String board_number = request.getParameter("board_number");
	MyDto dto = new MyDto();
	MyDao dao = new MyDao();
	dto = dao.selectOne(board_number);
%>
<section>
		<h1>Board</h1>
		<div class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th>번호 <%=dto.getBoard_number() %></th>
						<th>작성자 <%=dto.getBoard_writer() %></th>
						<th>제목 <%=dto.getBoard_title() %></th>
						<th>작성일 <%=dto.getBoard_date() %></th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tbl-content">
			<table cellpadding="0" cellspacing="0" border="0">
				
				<tbody>
					<tr>
						<td>
							 <%=dto.getBoard_content()%>
						</td>
					</tr>
				</tbody>
				
			</table>
			
		</div>
		<div>
		<button onclick="location.href='delete.jsp?board_number=<%=dto.getBoard_number()%>'">삭제</button>
		<button onclick="location.href='update.jsp?board_number=<%=dto.getBoard_number()%>'">수정</button>
		<button onclick="location.href='list.jsp'">목록</button>
		</div>
	</section>
</body>
</html>