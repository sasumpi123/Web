<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript">

	$(function(){
		$("#success").hide();
		$("#fail").hide();
		$("#usedid").hide();
		$("#idchk").hide();
		
		$("#pwchk").keyup(function(){
			var pw = document.getElementById("pw").value;
			var pwchk = document.getElementById("pwchk").value;
			if(pw == pwchk){
				$("#success").show();
				$("#fail").hide();
			} else {
				$("#success").hide();				
				$("#fail").show();
			}
		});
		
		$("#id").keyup(function(){
			$.ajax({
				url:"login.do?command=idchk&id="+$("#id").val(),
				dataType:"json",
				success:function(res){
					if(res.idchk == "true"){
						$("#usedid").show();
						$("#idchk").hide();
					} else {
						if($("#id").val() == null || $("#id").val() == "" ){
							$("#usedid").hide();
							$("#idchk").hide();
						} else {							
						$("#usedid").hide();
						$("#idchk").show();
						}
					}
				},
				error:function(){
					alert("통신 실패");
				}
			});
		});
	});

</script>
</head>
<body>
<%@include file="./form/header.jsp" %>
<form action="login.do" method="post">
<input type="hidden" name="command" value="registres"/>
	<table border="1" style="margin-left: auto; margin-right: auto;">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" id="id"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="idchk" style="color: blue">사용할 수 있는 아이디입니다.</div>
				<div id="usedid" style="color: red">사용중인 아이디 입니다</div>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pw" id="pw"/></td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td><input type="password" id="pwchk"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="success" style="color: blue">비밀번호가 일치합니다.</div>
				<div id="fail" style="color: red">비밀번호가 일치하지 않습니다.</div>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name"/></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="addr"/></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="phone"/></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email"/></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="가입"/>
				<input type="button" value="취소" onclick="history.back();"/>
				<input type="button" value="메인" onclick="location.href='board.do?command=list'"/>
			</td>
		</tr>
	</table>
</form>
<%@include file="./form/footer.jsp" %>
</body>
</html>