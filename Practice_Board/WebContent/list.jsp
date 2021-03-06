<%@page import="com.my.dao.MyDao"%>
<%@page import="com.my.dto.MyDto"%>
<%@page import="java.util.*"%>
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
</head>

<%
	MyDto dto = new MyDto();
	MyDao dao = new MyDao();
	List<MyDto> list = dao.selectList();
%>
<body>
	<section>
	
	<form action="muldelete.jsp" method="get">
	<button onclick="location.href='muldelete.jsp'">선택삭제</button>
		<h1>Board</h1>
		<div class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th>번호</th>
						<th>작성자</th>
						<th>제목</th>
						<th>작성일</th>
						<th><input type="checkbox" name="all" onclick="allChk(this.checked)">선택</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tbl-content">
			<table cellpadding="0" cellspacing="0" border="0">
				<%
					for (int i = 0; i < list.size(); i++) {
				%>
				<tbody>
					<tr>
						<td><%=list.get(i).getBoard_number()%></td>
						<td><%=list.get(i).getBoard_writer()%></td>
						<td><a href="detail.jsp?board_number=<%=list.get(i).getBoard_number()%>" style="text-decoration:none"><%=list.get(i).getBoard_title()%></a></td>
						<td><%=list.get(i).getBoard_date()%></td>
						<td><input type="checkbox" name="chk" value="<%=list.get(i).getBoard_number()%>"></td>
					</tr>
				</tbody>
				<%
					}
				%>
			</table>
		</div>
		
		</form>
		<button onclick="location.href='insert.jsp'">새글</button>
	</section>
	<!-- follow me template -->
	<div class="made-with-love">
		Made with <i>♥</i> by <a target="_blank"
			href="https://codepen.io/nikhil8krishnan">Nikhil Krishnan</a>
	</div>

<script type="text/javascript" src=""></script>
<script type="text/javascript">
function allChk(bool){
	var chks = document.getElementsByName("chk");
	for(var i=0; i<chks.length; i++){
		chks[i].checked = bool;
	}
}
</script>
</body>
</html>