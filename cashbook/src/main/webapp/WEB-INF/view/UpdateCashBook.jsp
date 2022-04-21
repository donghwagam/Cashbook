<%@page import="vo.Cashbook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>UpdateCashbook</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<%
		// 정보 받아오기
		String cashDate = "";
		if(request.getParameter("cashDate") != null) {
			cashDate = request.getParameter("cashDate");
		}
		
		int cash = 0;
		if(request.getParameter("cash") != null) {
			cash = Integer.parseInt(request.getParameter("cash"));
		}
		
		String memo = "";
		if(request.getParameter("memo") != null) {
			memo = request.getParameter("memo");
		}
		
		// 
	%>
	<div class="container bg-dark">
		<h1 class="text-white text-center">수정페이지</h1>
		<form method="post" action="<%=request.getContextPath()%>/UpdateCashBookController">
			<table class="table table-bordered text-center table-striped table-dark">
				<tr>
					<td>cashDate</td>
					<td><input name="cashDate" value="<%=cashDate%>" readonly="readonly"></td>
				</tr>
				<tr>
					<td>kind</td>
					<td>
						<select name="kind">
							<option value="수입">수입</option>
							<option value="지출">지출</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>cash</td>
					<td><input type="number" name="cash" value="<%=cash%>"></td>
				</tr>
				<tr>
					<td>memo</td>
					<td><textarea rows="10" cols="50" name="memo" value="<%=memo%>"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit">수정</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>