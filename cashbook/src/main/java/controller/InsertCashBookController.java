package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;
import vo.Cashbook;

@WebServlet("/InsertCashBookController")
public class InsertCashBookController extends HttpServlet {
	
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
		
		// 정보 받아오기
		int year = 0;
		if(request.getParameter("year") != null) {
			year = Integer.parseInt(request.getParameter("year"));
		}
		
		int month = 0;
		if(request.getParameter("month") != null) {
			month = Integer.parseInt(request.getParameter("month"));
		}
		
		int day = 0;
		if(request.getParameter("day") != null) {
			day = Integer.parseInt(request.getParameter("day"));
		}
		
		// 디버깅
		System.out.println("InsertCashBookController.doGet -> year : " + year);
		System.out.println("InsertCashBookController.doGet -> month : " + month);
		System.out.println("InsertCashBookController.doGet -> day : " + day);
		
		String cashDate = year+"-"+month+"-"+day; // 선택한 날짜
		
		request.setAttribute("cashDate", cashDate); // InsertCashbook.jsp에 값을 넘겨주기 위해 저장
		
		request.getRequestDispatcher("/WEB-INF/view/InsertCashBook.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션생성
		HttpSession session = request.getSession();
		// 세션ID를 가져오기
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
						
		// 로그인이 하지 않은 상태라면
		if(sessionMemberId == null) {
				response.sendRedirect(request.getContextPath()+"/LoginController");
				return;
		}
		
		request.setCharacterEncoding("utf-8"); // 한글이 깨지지 않게 설정
		
		// 가계부입력페이지로부터 입력값 받아오기
		String cashDate = "";
		if(request.getParameter("cashDate") != null) {
			cashDate = request.getParameter("cashDate");
		}
		
		String kind = "";
		if(request.getParameter("kind") != null) {
			kind = request.getParameter("kind");
		}
		
		int cash = 0;
		if(request.getParameter("cash") != null) {
			cash = Integer.parseInt(request.getParameter("cash"));
		}
		
		String memo = "";
		if(request.getParameter("memo") != null) {
			memo = request.getParameter("memo");
		}
		
		// 디버깅
		System.out.println("InsertCashBookController.doPost().kind : " + kind);
		System.out.println("InsertCashBookController.doPost().cash : " + cash);
		System.out.println("InsertCashBookController.doPost().memo : " + memo);
		System.out.println("InsertCashBookController.doPost().cashDate : " + cashDate);
		
		// ------------------------------- 해시태그 구현 시작 -------------------------------
		List<String> hashtag = new ArrayList<>();
		// 1) #앞에 띄어쓰기
		String memo2 = memo.replace("#", " #");
		// 2) 공백기준으로 문자열 쪼개기
		String[] arr = memo2.split(" ");
		
		for(String s : arr) {
			// 3) #로 시작하면
			if(s.startsWith("#")) {
				// 4) #가 없는상태로
				String temp = s.replace("#", "");
				// 5) 문자를 저장
				if(temp.length()>0) {
					hashtag.add(temp);
				}
			}
		}
		// 디버깅
		System.out.println("InsertCashBookController.doPost().hashtag.size() : " + hashtag.size());
		for(String s : hashtag) {
			System.out.println("InsertCashBookController.doPost().s : " + s);
		}
		// ------------------------------- 해시태그 구현 끝 -------------------------------
		
		// Cashbook 객체 생성
		Cashbook cashbook = new Cashbook(); 
		// 정보를 객체에 저장
		cashbook.setKind(kind);
		cashbook.setCashDate(cashDate);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);
		// 가계부입력메서드 적용
		CashbookDao cashbookDao = new CashbookDao(); // 메서드 사용을 위한 객체 생성
		cashbookDao.insertCashbook(cashbook, hashtag, sessionMemberId); // 메서드 사용
		
		// 가계부입력 성공시 가계부목록으로 돌아가기
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}
}
