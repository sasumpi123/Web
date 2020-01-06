<%@page import="com.dao.MemberDao"%>
<%@page import="com.dto.MemberDto"%>
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
		String member_id = request.getParameter("member_id"); // member_id 값을 받아옴
		session.setAttribute("id", member_id); // member_id 값을 id이름으로 세션에 등록
		String member_password = request.getParameter("member_password"); // member_password 값을 받아옴
		session.setAttribute("password", member_password); // member_password 값을 password 이름으로 세션에 등록
		MemberDto dto = new MemberDto();
		MemberDao dao = new MemberDao();

		
		// 세션에 모든 데이터들은 object형식으로 리턴되기 때문에 값을 가져올때 타입을 지정해줘야함
		String id = "";
		id = (String) session.getAttribute("id"); 
		String password = "";
		password = (String) session.getAttribute("password");
		dto.setMember_id(id);
		dto.setMember_password(password);

		if (dao.checkId(dto) > 0) {
	%>
	<script type="text/javascript">
		alert("로그인에 성공하였습니다");
		
		location.href = 'mylist.jsp';
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("아이디/비밀번호를 확인해주세요");
		location.href = "login.jsp";
	</script>
	<%
		}
	%>

</body>
</html>