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
		String member_id = request.getParameter("member_id");				
		String member_password = request.getParameter("member_password");
		MemberDto dto = new MemberDto();
		MemberDao dao = new MemberDao();
		dto.setMember_id(member_id);
		dto.setMember_password(member_password);
		// member_id, member_password 받아와 dto에 저장하고
		// dao.signIn함수를 통해 결과확인
		if (dao.signIn(dto) > 0) {
	%>
	<script type="text/javascript">
		alert("회원가입에 성공하였습니다");
		location.href = "login.jsp";
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("아이디/비밀번호를 확인해주세요");
		location.href = "signin.jsp";
	</script>
	<%
		}
	%>
</body>
</html>