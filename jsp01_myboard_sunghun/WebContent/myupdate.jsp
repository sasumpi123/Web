<%@page import="com.dto.MyDto"%>
<%@page import="com.dao.MyDao"%>
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
<body>

	<%
		String id = (String)session.getAttribute("id");					// 세션에서 id값 받아옴
		int myno = Integer.parseInt(request.getParameter("myno"));		// 번호값 받아옴
		MyDao dao = new MyDao();
		MyDto dto = new MyDto();
		dto = dao.selectOne(myno);										
		if((id).compareTo(dto.getMyname())!=0){							// 현재 접속해있는 아이디와 작성된 글의 작성자 아이디가 일치하지 않다면
																		// 글 수정 권한 없음
			%>
			<script type="text/javascript">
			alert("권한없음");
			location.href='mydetail.jsp?myno=<%=myno%>';
			
			</script>
			
			<%
		}
		
		
	%>
	<h1>수정</h1>
	<form action="myupdateres.jsp" method="post">
	<input type="text" name="myno" style="display: none" value="<%=dto.getMyno()%>">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><%=dto.getMyname()%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="mytitle" value=<%=dto.getMytitle() %>></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="6" cols="60" name="mycontent" ><%=dto.getMycontent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="수정" onclick="location.href='myupdateres.jsp'"> 
				<input type="button" value="취소" onclick="location.href='mylist.jsp'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>