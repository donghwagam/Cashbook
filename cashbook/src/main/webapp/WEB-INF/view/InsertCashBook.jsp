<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertCashBook</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/Style.css">
</head>
<body>
<div class="container-fluid">
	<div class="cash_main">
	
	<br><br>
	<h1 class="text-center text-dark" style="font-size: 50px;">가계부 입력</h1>
	<br><br>
	
	<div class="float-left">
	<a href="<%=request.getContextPath()%>/TagController" class="btn btn-outline-dark">#TAG</a>
	<a href="<%=request.getContextPath() %>/CashBookListByMonthController" class="btn btn-outline-dark">가계부</a>
	</div>
	
	<div class="float-right">
		<a href="<%=request.getContextPath() %>/SelectMemberOneController?memberId=<%=session.getAttribute("sessionMemberId") %>" class="text-dark">[<%=session.getAttribute("sessionMemberId") %>]</a>님 반갑습니다.&nbsp;
		<a href="<%=request.getContextPath() %>/LogoutController" class="btn btn-outline-dark btn-sm">LOGOUT</a>
	</div><br><br><br><br>
	
	<form method="post" action="<%=request.getContextPath()%>/InsertCashBookController">
		<table class="table table-bordered">
			<tr>
				<th class="text-center table-dark">날짜</th>
				<td>
					<input type="text" name="cashDate" value="<%=(String)request.getAttribute("cashDate")%>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th class="text-center table-dark">종류</th>
				<td>
					<input type="radio" name="kind" value="수입">수입
					<input type="radio" name="kind" value="지출">지출
				</td>
			</tr>
			<tr>
				<td class="text-center table-dark">금액</td>
				<td><input type="number" name="cash">원</td>
			</tr>
			<tr>
				<td class="text-center table-dark">내역</td>
				<td>
					<textarea rows="3" cols="50" name="memo"></textarea>
				</td>
			</tr>
		</table>
		<button type="submit" class="btn btn-outline-dark float-right">입력</button>
	</form>
	</div>
</div>
</body>
</html>