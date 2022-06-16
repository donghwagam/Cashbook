<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TagOne</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/Style.css">
</head>
<body>
<%
	int tagCount = (Integer)request.getAttribute("tagCount");
	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
%>
<div class="container-fluid">
	<div class="cash_main">
	
	<br><br>
	<h1 class="text-center text-dark" style="font-size: 50px;">태그 상세보기</h1>
	
	<h3 class="text-center text-secondary">total : <%=tagCount%>개</h3>
	
	<div class="float-left">
	<a href="<%=request.getContextPath()%>/TagController" class="btn btn-outline-dark">#TAG</a>
	<a href="<%=request.getContextPath() %>/CashBookListByMonthController" class="btn btn-outline-dark">가계부</a>
	</div>
	
	<div class="float-right">
		<a href="<%=request.getContextPath() %>/SelectMemberOneController?memberId=<%=session.getAttribute("sessionMemberId") %>" class="text-dark">[<%=session.getAttribute("sessionMemberId") %>]</a>님 반갑습니다.&nbsp;
		<a href="<%=request.getContextPath() %>/LogoutController" class="btn btn-outline-dark btn-sm">LOGOUT</a>
	</div><br><br><br><br>
	
	<table class="table table-bordered">
		<thead class="table-dark">
			<tr>
				<th>해시태그</th>
				<th>날짜</th>
				<th>수입/지출</th>
				<th>메모</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(Map<String, Object> map : list) {
			%>
					<tr>
						<td><%=map.get("tag")%></td>
						<td><%=map.get("cashDate")%></td>
						<td><%=map.get("kind")%></td>
						<td><%=map.get("memo")%></td>
					</tr>
			<%
				}
			%>
		</tbody>
	</table>
	</div>
</div>
</body>
</html>