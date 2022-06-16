<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TagKindSearch</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/Style.css">
</head>
<body>
<div class="container-fluid">
	<div class="cash_main">
	
	<br><br>
	<h1 class="text-center text-dark" style="font-size: 50px;">수입/지출별 검색</h1>
	
	<div class="float-left">
		<a href="<%=request.getContextPath() %>/CashBookListByMonthController" class="btn btn-outline-dark">가계부</a>
		<a href="<%=request.getContextPath()%>/TagKindSearchController" class="btn btn-outline-dark">수입/지출별 검색</a>
		<a href="<%=request.getContextPath()%>/TagDateSearchController" class="btn btn-outline-dark">날짜별 검색</a>
	</div>
	
	<div class="float-right">
		<a href="<%=request.getContextPath() %>/SelectMemberOneController?memberId=<%=session.getAttribute("sessionMemberId") %>" class="text-dark">[<%=session.getAttribute("sessionMemberId") %>]</a>님 반갑습니다.&nbsp;
		<a href="<%=request.getContextPath() %>/LogoutController" class="btn btn-outline-dark btn-sm">LOGOUT</a>
	</div><br><br><br><br>
	
	<form method="get" action="<%=request.getContextPath()%>/TagKindSearchController">
		<table class="table table-bordered">
			<tr class="table">
				<td>선택</td>
				<td>
					<input type="radio" name="kind" value="수입"> 수입
					<input type="radio" name="kind" value="지출"> 지출
				</td>
				<td>
					<button type="submit" class="btn btn-dark">검색</button>
				</td>
			</tr>
		</table>
	</form>
	<%
		if(request.getAttribute("kind")!=null) {
			String kind = (String)request.getAttribute("kind");
			List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
	%>
			<h2 class="text-center"><%=kind%></h2>
			<table class="table table-bordered">
				<thead class="table-dark">
					<tr>
						<th>순위</th>
						<th>해시태그</th>
						<th>개수</th>
					</tr>
				</thead>
				<tbody>
					<%
						for(Map<String, Object> map : list) {
					%>
							<tr>
								<td><%=map.get("rank")%></td>
								<td><%=map.get("tag")%></td>
								<td><%=map.get("cnt")%></td>
							</tr>
					<%
						}
					%>
				</tbody>
			</table>
	<%
		}
	%>
	</div>
</div>
</body>
</html>