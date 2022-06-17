<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashBookListByMonth</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style/Style.css">
</head>
<body>
	<%
		// 컨트롤러에서 list에 담은 값 불러오기
		List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
		
		int year = (Integer)request.getAttribute("year");
		int month = (Integer)request.getAttribute("month");
		int cashbookNo = (Integer)request.getAttribute("cashbookNo");
		
		int startBlank = (Integer)request.getAttribute("startBlank");
		int endDay = (Integer)request.getAttribute("endDay");
		int endBlank = (Integer)request.getAttribute("endBlank");
		int totalTd = (Integer)request.getAttribute("totalTd");
		
		System.out.println(list.size() +" <- list.size() CashBookListByMonth.jsp");
		System.out.println(year +" <- year CashBookListByMonth.jsp");
		System.out.println(month +" <- month CashBookListByMonth.jsp");
		
		System.out.println(startBlank +" <- startBlank CashBookListByMonth.jsp");
		System.out.println(endDay +" <- endDay CashBookListByMonth.jsp");
		System.out.println(endBlank +" <- endBlank CashBookListByMonth.jsp");
		System.out.println(totalTd +" <- totalTd CashBookListByMonth.jsp");
	
		for(Map map : list) {
			System.out.println(map.get("cashbookNo"));
		}
				
	%>
<div class="container-fluid">
	<div class="cash_main">
	<br><br>
	<h1 class="text-center text-dark" style="font-size: 50px;">
		<a href="<%=request.getContextPath()%>/CashBookListByMonthController?year=<%=year%>&month=<%=month-1%>" class="text-dark">◀</a>
		<%=year%>년 <%=month%>월
		<a href="<%=request.getContextPath()%>/CashBookListByMonthController?year=<%=year%>&month=<%=month+1%>" class="text-dark">▶</a>
	</h1>
	<br><br>
	
	
	<a href="<%=request.getContextPath()%>/TagController" class="btn btn-outline-dark float-left">#TAG</a>
	<div class="float-right">
		<a href="<%=request.getContextPath() %>/SelectMemberOneController?memberId=<%=session.getAttribute("sessionMemberId") %>" class="text-dark">[<%=session.getAttribute("sessionMemberId") %>]</a>님 반갑습니다.&nbsp;
		<a href="<%=request.getContextPath() %>/LogoutController" class="btn btn-outline-dark btn-sm">LOGOUT</a>
	</div><br><br><br>
	
	
	
	<!-- 
		1) 이번날 1일의 요일 firstDayYoil -> startBlank -> 일 0, 월 1, 화 2, ... 토 6
		2) 이번달 마지막날짜 endDay
		3) endBlank -> totalBlank
		4) td의갯수 1 ~ totalBlank
		5) 가계부 list
		6) 오늘 날짜
	-->
	<table class="table table-bordered" style="table-layout: fixed;">
		<thead class="text-center table-dark">
			<tr>
				<th class="text-danger">일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th class="text-primary">토</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%	
					// 날짜 출력
					for(int i=1; i<=totalTd; i+=1) {
						// 1일부터 마지막 날짜만 출력
						if((i-startBlank) > 0 && (i-startBlank) <= endDay) {
							String c ="";
							if(i%7==0) {
								c = "text-primary";
							} else if(i%7==1) {
								c = "text-danger";
							}
				%>
						<td class="<%=c%>" style="height: 90px;" width="15%">
							<%=i-startBlank%>
							
							<a href="<%=request.getContextPath()%>/InsertCashBookController?year=<%=year%>&month=<%=month%>&day=<%=i-startBlank%>" class="btn btn-outline-dark text-dark float-right">입력</a>
							
							<div>
								<%
									// 해당날짜의 cashbook목록 출력
									for(Map map: list) {
										if((Integer)map.get("day") == (i-startBlank)) {
								%>
										<div>
											<a class="text-dark" href="<%=request.getContextPath()%>/CashBookOneController?cashbookNo=<%=map.get("cashbookNo")%>">
								<%
											if("수입".equals(map.get("kind"))) {
								%>
												<span class="text-primary">[<%=map.get("kind") %>]</span>
								<% 				
											} else {
								%>
												<span class="text-danger">[<%=map.get("kind") %>]</span>								
								<%
											}
								%>
											<%=map.get("cash") %>원 <!-- 금액 -->
											<%=map.get("memo") %>... <!-- 내역 -->
											</a>
										</div>	
								<%			
										}
									}
								%>
							</div>
						</td>	
				<% 
						} else {
				%>					
							<td>&nbsp;</td>
				<%
						}
						if(i<totalTd && i%7==0) {
				%>			
						</tr><tr>
				<%
						}
					}	
				%>		
			</tr>
		</tbody>	
	</table>
	</div>
</div>	
</body>
</html>