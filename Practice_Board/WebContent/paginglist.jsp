<%@page import="com.paging.paging"%>
<%@page import="com.dao.MyDao"%>
<%@page import="com.dto.MyDto"%>
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
	int Page = Integer.parseInt(request.getParameter("Page"));	// 현재 페이지
	MyDao dao = new MyDao();						
	paging pg = new paging();									// paging객체
	pg.setPage(Page);											// 현재 페이지 설정
	int startpage = (((Page - 1) / pg.getColPage()) * 10) + 1;	// paging 시작기준 잡아줌
	int endpage = startpage + pg.getColPage() - 1;				// paging 끝기준 잡아줌

	int end = Page * pg.getColPage();							// 리스트 표시되는 글들 시작
	int start = end - pg.getColPage();							// 리스트 표시되는 글들 마지막
	int totalpage = (dao.selectList().size()) / pg.getColPage();// 전체 페이지 수 구하기 
	List<MyDto> list = dao.paging(start, end);					// 해당 페이지의 글들만 가져옴
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
							<th><input type="checkbox" name="all"
								onclick="allChk(this.checked)">선택</th>
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
							<td><a
								href="detail.jsp?board_number=<%=list.get(i).getBoard_number()%>"
								style="text-decoration: none"><%=list.get(i).getBoard_title()%></a></td>
							<td><%=list.get(i).getBoard_date()%></td>
							<td><input type="checkbox" name="chk"
								value="<%=list.get(i).getBoard_number()%>"></td>
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


		<a href="pagingres.jsp?Page=<%=startpage - 2%>" id="prev">prev</a>
		<%
			for (int i = startpage; i <= endpage; i++) {
				if (i < totalpage) {
		%>
		<a href="pagingres.jsp?Page=<%=i%>"><%=i%></a>
		<%
			}
			}
		%>
		<a href="pagingres.jsp?Page=<%=endpage + 1%>" id="next">next</a>
	</div>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		
	<%if (startpage == 1) {%>						// 현재 시작 페이지가 1일때
		$("#prev").css("display", "none");			// prev버튼 display:none
	<%} else {%>
		$("#prev").css("display", "");
	<%}%>
		
	<%if (startpage == ((totalpage/10)*10)+1) {%>	// 현지 시작페이지가 마지막 단일때
		$("#next").css("display", "none");			// next 버튼 display:none
	<%} else {%>
		$("#next").css("display", "");
	<%}%>
		
	</script>
	<script type="text/javascript">
		function allChk(bool) {
			var chks = document.getElementsByName("chk");
			for (var i = 0; i < chks.length; i++) {
				chks[i].checked = bool;
			}
		}
	</script>
</body>
</html>