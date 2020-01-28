<%@page import="java.util.Calendar"%>
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
<style type="text/css">
#calendar{
	border-collapse: collapse;
	border: 1px solid gray;
}
#calendar th{
	width: 80px;
	border: 1px solid gray;
}
#calendar td{
	width: 80px;
	height: 80px;
	border: 1px solid gray;
	text-align: left;
	vertical-align: top;
	position: relative;
}
#head{
background-color: skyblue;
}
</style>
</head>
<%
	// Calendar는 싱글톤으로 만들어져있기때문에 new객체로 생성할 수 없다.
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	// month - 0부터 시작
	int month = cal.get(Calendar.MONTH) + 1;
	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");

	if (paramYear != null) {
		year = Integer.parseInt(paramYear);
	}
	if (paramMonth != null) {
		month = Integer.parseInt(paramMonth);
	}

	if (month < 1) {
		month = 12;
		year--;
	} else if (month > 12) {
		month = 1;
		year++;
	}
	
	// 현재년도, 현재월, 해당 월의 1일로 셋팅
	cal.set(year,month-1,1);
	
	// 1일의 요일
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	
	// 현재 월의 마지막 일
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	// 달력에 일정 표현
	
	
%>
<body>

	<table id="calendar">
		<caption>
			<a href="calendar.jsp?year=<%=year-1%>&month=<%=month%>">◁</a>
			<a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>">◀</a>
			
			<span class="y"><%=year %></span>년
			<span class="m"><%=month %></span>월
			
			<a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">︎︎︎︎︎▶︎</a>
			<a href="calendar.jsp?year=<%=year+1%>&month=<%=month%>">▷</a>
		</caption>
		
		<tr id="head">
			<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
		</tr>
		<tr>
			<%
			// 달력 앞부분 공백
			for(int i=0; i<dayOfWeek-1; i++){
				out.print("<td>&nbsp</td>");
			}
			// 달력 날짜
			for(int i=1; i<=lastDay; i++){
				%>
				<td><a href="insertcalboard.jsp?year=<%=year%>&month=<%=month%>&date=<%=i%>&lastday=<%=lastDay%>"><%=i %> </a>
					<a>
						<img alt="일정추가" src="img/pen.png" style="width: 10px; height: 10px">
					</a>
					<a href="calboarddetail.jsp"></a>
				</td>
				<%
				if((i+dayOfWeek-1)%7==0){
					out.print("</tr><tr>");
				}
			}
			// 달력 뒷부분 공백
			int check = (dayOfWeek+lastDay-1)%7;
			while(true){
				if(check>6||check==0){
					break;
				}else{
					out.print("<td>&nbsp</td>");
					check++;	
				}
			}
			%>
			 </tr>
			
	</table>

</body>
</html>