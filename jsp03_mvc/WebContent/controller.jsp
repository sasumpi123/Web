<%@page import="java.util.List"%>
<%@page import="com.mvc.biz.MVCBiz"%>
<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="com.mvc.biz.MVCBizImpl"%>
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
		String command = request.getParameter("command");
			System.out.println("<" + command + ">");
			MVCBiz biz = new MVCBizImpl();

			// foward : 데이터를 넘겨줄때 보통 사용
			// response.sendRedirect : 화면전환만 할때 주로 사용

			if (command.equals("list")) { // 전체 리스트 출력
		// 1.
		// 2. biz.selectList()를 가져와 list<MVCDto>에 담는다
		List<MVCDto> list = biz.selectList();
		request.setAttribute("list", list);
		// 3. boardList.jsp 로 보냄
		pageContext.forward("boardlist.jsp"); // forward : 모든 권한을 넘긴다	
			} else if (command.equals("writeform")) { // 글쓰기(작성폼)
		// 1. 받을 데이터가 있는지 확인
		// 2. db에서 가져올 데이터가 있는지 확인
		// 3. 어디로 갈 것인지 확인
		response.sendRedirect("boardwrite.jsp");
			} else if (command.equals("writeres")) { // 글쓰기(업로드)
		// 1. 받을 데이터가 있는지 확인
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MVCDto dto = new MVCDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		// 2. db에서 가져올 데이터가 있는지 확인
		int res = biz.insert(dto);
		// 3. 어디로 갈 것인지 확인
		if (res > 0) {
	%>
	<script type="text/javascript">
		alert("새로운 글 등록 완료");
		location.href = "controller.jsp?command=list";
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("새로운 글 등록 실패");
		location.href = "controller.jsp?command=writeform";
	</script>
	<%
		}
			} else if (command.equals("muldel")) { // 다중삭제
		// 1. 받을 데이터가 있는지 확인
		String[] seqs = request.getParameterValues("chk");
		// 2. db에서 가져올 데이터가 있는지 확인
		boolean res = biz.multiDelete(seqs);
		// 3. 어디로 갈 것인지 확인
		if (res) {
	%>
	<script type="text/javascript">
		alert("선택 삭제 완료");
		location.href = "controller.jsp?command=list"
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("선택 삭제 실패");
		location.href = "controller.jsp?command=list"
	</script>
	<%
		}

			} else if (command.equals("detail")) { // 상세 페이지
		// 1. 받을 데이터가 있는지 확인
		int seq = Integer.parseInt(request.getParameter("seq"));
		// 2. db에서 가져올 데이터가 있는지 확인
		MVCDto dto = new MVCDto();
		dto = biz.selectOne(seq);
		// 3. 어디로 갈 것인지 확인
		request.setAttribute("dto", dto);
		// 3. boardList.jsp 로 보냄
		pageContext.forward("detail.jsp"); // forward : 모든 권한을 넘긴다
			} else if (command.equals("delete")) {
		String seq = request.getParameter("seq");
		System.out.println(seq);
		int res = biz.delete(seq);
		if (res > 0) {
	%>
	<script type="text/javascript">
		alert("삭제 완료");
		location.href = "controller.jsp?command=list"
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("삭제 실패");
		location.href = "controller.jsp?command=list"
	</script>
	%>
	<%
		}
			} else if (command.equals("update")) {
		// 1. 받을 데이터가 있는지 확인
		int seq = Integer.parseInt(request.getParameter("seq"));
		// 2. db에서 가져올 데이터가 있는지 확인
		MVCDto dto = new MVCDto();
		dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		// 3. update.jsp로 보냄
		pageContext.forward("update.jsp"); // forward : 모든 권한을 넘긴다

			} else if (command.equals("updateres")) {
		// 1. 받을 데이터가 있는지 확인
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		// 2.db로 데이터 보내주고 결과 받아옴
		MVCDto dto = new MVCDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setSeq(seq);
		int res = biz.update(dto);
		if (res > 0) {
	%>
	<script type="text/javascript">
		alert("수정 완료");
		location.href = "controller.jsp?command=list"
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("수정 실패");
		location.href = "controller.jsp?command=list"
	</script>
	<%
		}
		}
	%>



	<h1>잘못왔다(command 확인)</h1>
</body>
</html>