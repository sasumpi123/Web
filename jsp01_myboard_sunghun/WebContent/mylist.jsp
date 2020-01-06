<%@page import="com.dao.MyDao"%>
<%@page import="com.dto.MyDto"%>
<%@page import="com.dto.MemberDto"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF=8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		MyDao dao = new MyDao(); // Dao 객체 선언
		List<MyDto> list = dao.selectList(); // MyDto 타입의 list를 선언하고 dao의 selectList함수를 담아옴
		String id = "";
		id = (String) session.getAttribute("id"); // 세션에 모든 데이터들은 object형식으로 리턴되기 때문에 값을 가져올때 타입을 지정해줘야함

		if (id == null || id.equals("")) { // 세션에서 가져온 값이 없거나 null일경우 로그인 페이지로 리턴
			response.sendRedirect("login.jsp");
		}
	%>


	<h1>List</h1>
	<div>
		내 아이디 :<%=id%></div>

	<table border="1">
		<col width="50">
		<col width="100">
		<col width="200">
		<col width="100">

		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일(수정일)</th>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getMyno()%></td>
			<td><%=list.get(i).getMyname()%></td>
			<td><a
				href="mydetail.jsp?myno=<%=list.get(i).getMyno()%>"><%=list.get(i).getMytitle()%></a></td>
			<td><%=list.get(i).getMydate()%></td>
		</tr>

		<%
			}
		%>

		<tr>
			<td colspan="4" align="right"><input type="button"
				onclick="location.href = 'myinsert.jsp'"
				value="글쓰기">
		</tr>
	</table>
	<button onclick="location.href='myinfo.jsp'">내정보</button>
	<button onclick="location.href='logout.jsp'">로그아웃</button>
</body>
</html>