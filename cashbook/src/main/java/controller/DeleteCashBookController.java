package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;


@WebServlet("/DeleteCashBookController")
public class DeleteCashBookController extends HttpServlet {

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
				
		// 가계부상세보기페이지로부터 삭제할 번호 받아오기
		int cashbookNo = 0;
		if(request.getParameter("cashbookNo") != null) {
			cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		}
		System.out.println("DeleteCashBookController.doGet() cashbookNo : " + cashbookNo);
		
		// 가계부삭제메서드 받아오기
		CashbookDao cashbookDao = new CashbookDao(); // 메서드 사용을 위한 객체 생성
		cashbookDao.deleteCashbook(cashbookNo); // 메서드 사용 후 반환값 저장
		
		// 가계부삭제 성공시 가계부목록으로 리다이렉트
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}
}
