<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/Style.css">
</head>
<body>
<div class="container-fluid">
	<div class="bg_main">
		<div class="box">
			<div class="text-center">
			<div class="text-center text-white"><br>
				<h1>LOGIN</h1><br>
			</div>
			<form method="post" action="<%=request.getContextPath()%>/LoginController">
				<input type="text" name="memberId" placeholder="USER ID" class="form-control text-center"><br>
				<input type="password" name="memberPw" placeholder="PASSWORD" class="form-control text-center"><br>
				<button type="submit" class="btn btn-outline-light btn-block">LOG IN</button><hr>
				<a href="<%=request.getContextPath() %>/InsertMemberController" class="btn btn-outline-light btn-block">SIGN IN</a>
			</form>
			</div>
		</div>
	</div>	
</div>	
</body>
</html>