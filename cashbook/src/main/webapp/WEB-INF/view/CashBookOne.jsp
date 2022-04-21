<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashBookOne</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body class = "container-fluid">
	<%
	Cashbook cashBook = (Cashbook)request.getAttribute("cashBook");
	int cashbookNo = (Integer)request.getAttribute("cashbookNo");	
    // 디버깅
	System.out.println(cashBook +"<--cashBook CashBookOne.jsp");
	%>
	<h2 class = "bottom top text-center">CashBookOne</h2>
	<table class="table table-bordered">
		<colgroup>
			<col width = "20%">
			<col width = "*">
		</colgroup>
		<tr>
			<th class = "table-active">CashbookNo</th>
			<td><%=cashBook.getCashbookNo()%></td>
		</tr>
		<tr>
			<th class = "table-active">CashDate</th>
			<td><%=cashBook.getCashDate()%></td>
		</tr>
		<tr>
			<th class = "table-active">Kind</th>
			<td><%=cashBook.getKind()%></td>
		</tr>
		<tr>
			<th class = "table-active">Cash</th>
			<td><%=cashBook.getCash()%></td>
		</tr>
		<tr>
			<th class = "table-active">Memo</th>
			<td><%=cashBook.getMemo()%></td>
		</tr>
		<tr>
			<th class = "table-active">UpdateDate</th>
			<td><%=cashBook.getUpdateDate()%></td>
		</tr>
		<tr>
			<th class = "table-active">CreateDate</th>
			<td><%=cashBook.getCreateDate()%></td>
		</tr>
	</table>
	<a href="<%=request.getContextPath()%>/DeleteCashBookController" class = "btn btn-outline-dark">삭제</a>
	<a class="btn btn-primary" href="<%=request.getContextPath()%>/UpdateCashBookController?cashbookNo=<%=cashbookNo%>&cashDate=<%=cashBook.getCashDate()%>&kind=<%=cashBook.getKind()%>&cash=<%=cashBook.getCash()%>&memo=<%=cashBook.getMemo()%>">수정</a>
	<a href="<%=request.getContextPath()%>/CashBookListByMonthController" class = "float-right btn btn-outline-dark">이전</a>
</body>
</html>