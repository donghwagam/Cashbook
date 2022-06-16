package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;

// CONTROLLER
@WebServlet("/CashBookListByMonthController")
public class CashBookListByMonthController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션생성
		HttpSession session = request.getSession();
		// 세션ID를 가져오기
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
						
		// 로그인이 하지 않은 상태라면
		if(sessionMemberId == null) {
				response.sendRedirect(request.getContextPath()+"/LoginController");
				return;
		}
		
		// 1) 월별 가계부 리스트 요청 분석
		
		// 현재의 연-월을 초기화값으로 저장
		Calendar now = Calendar.getInstance(); // ex) 2022.04.19
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // 0 - 1월, 1 - 2월, ... 11 - 12월
		
		// CashBookListByMonth.jsp로 부터 받은 값을 저장
		if(request.getParameter("year") != null) {
			year = Integer.parseInt(request.getParameter("year"));
		}
		if(request.getParameter("month") != null) {
			month = Integer.parseInt(request.getParameter("month"));
		}
		
		
		if(month==0) {
			month = 12;
			year = year-1;
		}
		if(month==13) {
			month = 1;
			year = year+1;
		}
		
		System.out.println(year+" <-- year");
		System.out.println(month+" <-- month");
		
		
		/*===========================================================================================*/
		/*
		 <캘린더 생성시 필요한 값들>
		 1) startBlank
		 2) endDay
		 3) endBlank
		 4) totalBlank
		 */
		
		// 시작시 필요한 공백 <TD>의 갯수를 구하는 알고리즘 -> startBlank 
		// firstDay는 오늘날짜를 먼저구하여 날짜만 1일로 변경해서 구하자
		Calendar firstDay = Calendar.getInstance(); // ex) 2022.04.19
		firstDay.set(Calendar.YEAR, year);
		firstDay.set(Calendar.MONTH, month-1); // 자바 달력API는 1월을 0으로, 2월을 1로, ... 12월을 11로 설정되어있음
		firstDay.set(Calendar.DATE, 1); // ex) 2022.04.01
		// 1. 일1, 월2, ... 토7
		int dayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);
		// 2. 일0, 월1, ... 토6
		int startBlank = dayOfWeek - 1;
		// 3. 마지막 날짜는 자바 달력api를 이요하여 구하자
		int endDay = firstDay.getActualMaximum(Calendar.DATE);// firstDay달의 제일 큰수자 -> 마지막날짜
		// 4. strartBlank와 endDay를 합의 결과에 endBlank를 더해서 7의 배수가 되도록
		int endBlank = 0;
		if((startBlank+endDay)%7 != 0) {
			// 7에서 startBlank+endDay의 7로 나눈 나머지값을 빼면
			endBlank = 7-((startBlank+endDay)%7);
		}
		// 5.
		int totalTd = startBlank + endDay + endBlank;
		
		
		/*===========================================================================================*/
		
		// 2) 월별가계부목록 메서드를 적용 
		CashbookDao cashbookDao = new CashbookDao();
		List<Map<String, Object>> list = cashbookDao.selectCashbookListByMonth(year, month, sessionMemberId);
		
		// 달력출력에 필요한 값들 담기
		request.setAttribute("startBlank", startBlank);
		request.setAttribute("endDay", endDay);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("totalTd", totalTd);
		
		// DB의 값들 담기
		request.setAttribute("list", list);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		
		// 3) 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/CashBookListByMonth.jsp").forward(request, response);
	}

}