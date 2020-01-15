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

<!-- page -> request -> session|application 순으로 해당 객체를 탐색 -->
<!-- ${객체이름.가져올변수}해당 객체의 get함수를 불러옴 -->
	<h1>EL Test</h1>

	<table border="1">
		<tr>
			<th colspan="2">${hong.name}님의점수는</th>
		</tr>
		<tr>
			<th>국어</th>
			<th>${hong.kor}</th>
		</tr>
		<tr>
			<th>수학</th>
			<th>${hong.math}</th>
		</tr>
		<tr>
			<th>영어</th>
			<th>${hong.eng}</th>
		</tr>
		<tr>
			<th>총점</th>
			<th>${hong.sum}</th>
		</tr>
		<tr>
			<th>평균</th>
			<th>${hong.avg}</th>
		</tr>
		<tr>
			<th>등급</th>
			<th>${hong.grade}</th>
		</tr>	
	</table>


</body>
</html>