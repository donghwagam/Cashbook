<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UpdateMemberPW</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/Style.css">
</head>
<body>
<div class="container-fluid">
	<div class="cash_main">
	
	<br><br>
	<h1 class="text-center text-dark" style="font-size: 50px;">회원탈퇴</h1>
	
	<div class="float-left">
	<a href="<%=request.getContextPath()%>/TagController" class="btn btn-outline-dark">#TAG</a>
	<a href="<%=request.getContextPath() %>/CashBookListByMonthController" class="btn btn-outline-dark">가계부</a>
	</div>
	
	<div class="float-right">
		<a href="<%=request.getContextPath() %>/SelectMemberOneController?memberId=<%=session.getAttribute("sessionMemberId") %>" class="text-dark">[<%=session.getAttribute("sessionMemberId") %>]</a>님 반갑습니다.&nbsp;
		<a href="<%=request.getContextPath() %>/LogoutController" class="btn btn-outline-dark btn-sm">LOGOUT</a>
	</div><br><br><br><br>
	
	<form method="post" action="<%=request.getContextPath() %>/UpdateMemberPwController">
		<table class="table table-bordered">
			<tr>
				<th class="text-center table-dark">기존 비밀번호</th>
				<td><input type="password" name="memberPw"></td>
			</tr>
			<tr>
				<th class="text-center table-dark">변경 비밀번호</th>
				<td><input type="password" name="memberPw2"></td>
			</tr>
		</table>
		<button type="submit" class="btn btn-outline-dark float-right">비밀번호 변경</button>
	</form>
	</div>
</div>
</body>
</html>