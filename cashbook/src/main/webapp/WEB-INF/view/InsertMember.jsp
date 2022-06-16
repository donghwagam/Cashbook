<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertMember</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/Style.css">
</head>
<body>
<div class="container-fluid">
	<div class="bg_main">
		<div class="box">
			<div class="text-center">
			<div class="text-center text-white"><br>
				<h1>SIGN IN</h1><br>
			</div>
			<form method="post" action="<%=request.getContextPath() %>/InsertMemberController">
					<input type="text" name="memberId" placeholder="ID를 입력하세요." class="form-control text-center"><br>
					<input type="password" name="memberPw" placeholder="PW를 입력하세요." class="form-control text-center"><br>
					<input type="password" name="memberPw2" placeholder="PW를 한번 더 입력하세요." class="form-control text-center"><hr>
				<button type="submit" class="btn btn-outline-light btn-block">SIGN IN</button>
			</form>
			</div>
		</div>
	</div>	
</div>	
</body>
</html>