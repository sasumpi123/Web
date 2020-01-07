<%@page import="com.dao.MyDao"%>
<%@page import="com.dto.MyDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<link rel="stylesheet" type="text/css" href="src/listcss.css">


<%
	String board_number = request.getParameter("board_number");
	MyDto dto = new MyDto();
	MyDao dao = new MyDao();
	dto = dao.selectOne(board_number);
%>
</head>
<body>
	<section>
		<form action="updateres.jsp" method="get">
			<input type="text" name="board_number" style="display: none;"
				value="<%=dto.getBoard_number()%>">
			<h1>Board</h1>
			<div class="tbl-header">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th>번호 <%=dto.getBoard_number()%></th>
							<th>작성자 <%=dto.getBoard_writer()%></th>
							<th>제목 <input type="text" value="<%=dto.getBoard_title()%>"
								name="board_title"></th>
							<th>작성일 <%=dto.getBoard_date()%></th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="tbl-content">
				<table cellpadding="0" cellspacing="0" border="0">

					<tbody>
						<tr>
							<td><textarea rows="10" cols="251" name="board_content"><%=dto.getBoard_content()%></textarea>

							</td>
						</tr>
					</tbody>

				</table>

			</div>
			<button onclick="location.href='updateres.jsp'">수정</button>
		</form>
		<button
			onclick="location.href='detail.jsp?board_number=<%=board_number%>'">취소</button>

	</section>

</body>
</html>