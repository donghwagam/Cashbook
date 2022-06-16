<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashBookOne</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/Style.css">
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body>
	<%
		List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
	%>
	<div class="cash_main">
	
	<br><br>
	<h1 class="text-center text-dark" style="font-size: 50px;">가계부 상세보기</h1>
	<br><br>
	
	<div class="float-left">
	<a href="<%=request.getContextPath()%>/TagController" class="btn btn-outline-dark">#TAG</a>
	<a href="<%=request.getContextPath() %>/CashBookListByMonthController" class="btn btn-outline-dark">가계부</a>
	</div>
	
	<div class="float-right">
		<a href="<%=request.getContextPath() %>/SelectMemberOneController?memberId=<%=session.getAttribute("sessionMemberId") %>" class="text-dark">[<%=session.getAttribute("sessionMemberId") %>]</a>님 반갑습니다.&nbsp;
		<a href="<%=request.getContextPath() %>/LogoutController" class="btn btn-outline-dark btn-sm">LOGOUT</a>
	</div><br><br><br><br>
	
	<table class="table table-bordered">
		<% 
			for(Map map : list) {
		%>
			<tr>
				<th class="text-center table-dark">번호</th>
				<td><%=map.get("cashbookNo")%></td>
			</tr>
			<tr>
				<th class="text-center table-dark">날짜</th>
				<td><%=map.get("cashDate")%></td>
			</tr>
			<tr>
				<th class="text-center table-dark">종류</th>
				<td><%=map.get("kind")%></td>
			</tr>
			<tr>
				<th class="text-center table-dark">금액</th>
				<td><%=map.get("cash")%></td>
			</tr>
			<tr>
				<th class="text-center table-dark">내역</th>
				<td><%=map.get("memo")%></td>
			</tr>
	</table>
	<div>
	 <a href="<%=request.getContextPath()%>/DeleteCashBookController?cashbookNo=<%=map.get("cashbookNo")%>" class="btn btn-outline-dark float-right">삭제</a> 
	</div>
		<%
			}
		%>
</body>
</html>