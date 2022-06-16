package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;

@WebServlet("/CashBookOneController")
public class CashBookOneController extends HttpServlet {

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
		
		// 가계부상세보기페이지로부터 입력값 받아오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		
		// selectCashBookOne메서드를 적용하여 상세정보 저장 
		CashbookDao cashbookDao = new CashbookDao();
		List<Map<String, Object>> list = cashbookDao.selectCashBookOne(cashbookNo);
		request.setAttribute("list", list); 
	
		// 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/CashBookOne.jsp").forward(request, response);
	}
}