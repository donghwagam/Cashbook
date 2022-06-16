<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberOne</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/Style.css">
</head>
<body>
<%
	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
%>
<div class="container-fluid">
	<div class="cash_main">
	
	<br><br>
	<h1 class="text-center text-dark" style="font-size: 50px;">회원정보 상세보기</h1>
	
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
			for(Map<String,Object> map : list) {
		%>
		<tr>
			<th class="text-center table-dark">아이디</th>
			<td><%=map.get("memberId") %></td>
		</tr>	
		<tr>
			<th class="text-center table-dark">생성일</th>
			<td><%=map.get("createDate") %></td>
		</tr>	
		<%
			}
		%>
	</table>
	<div>
	<a href="<%=request.getContextPath() %>/UpdateMemberPwController"  class="btn btn-outline-dark float-right">수정</a>
	<a href="<%=request.getContextPath() %>/DeleteMemberController"  class="btn btn-outline-dark float-right">탈퇴</a>
	</div>

	</div>
</div>

</body>
</html>