<%@page import="com.mvc.dao.MVCDao"%>
<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="java.util.List"%>
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
</head>

<%
	List<MVCDto> list = (List<MVCDto>) request.getAttribute("list");
	MVCDto dto = null;
%>

<body>
	<script type="text/javascript">
		function AllChk(bool) {
			chks = document.getElementsByName("chk");
			for (var i = 0; i < chks.length; i++) {
				chks[i].checked = bool;
			}
		}
	</script>

	<h1>글 목록</h1>
	<form action="mvc.do" method="post">
		<input type="hidden" name="command" value="muldelete">
		<table border="1">
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
				<th><input type="checkbox" name="all"
					onclick="AllChk(this.checked)">전체선택</th>
			</tr>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr>
				<td><%=list.get(i).getMyno()%></td>
				<td><%=list.get(i).getMywriter()%></td>
				<td><a
					href="mvc.do?command=detail&myno=<%=list.get(i).getMyno()%>"><%=list.get(i).getMytitle()%></a></td>
				<td><%=list.get(i).getMydate()%></td>
				<td><input type="checkbox" name="chk"
					value="<%=list.get(i).getMyno()%>"></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td><input type="submit" value="선택삭제"></td>
				<td><input type="button" value="글쓰기"
					onclick="location.href='mvc.do?command=insert'"></td>
			</tr>

		</table>
	</form>
</body>
</html>