package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;

@WebServlet("/CashBookOneController")
public class CashBookOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//요청값 받아오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		
		CashbookDao cashBookDao = new CashbookDao();
		
		// cashBook에 selectCashBookOne메서드로 호출한 상세정보 저장
		Cashbook cashBook = cashBookDao.selectCashBookOne(cashbookNo);
		request.setAttribute("cashBook", cashBook); // CashbookOne.jsp에 값 넘겨주기 위해 저장
		request.setAttribute("cashbookNo", cashbookNo); // 삭제 수정을 위한 정보의 번호 저장
	
		// 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/CashBookOne.jsp").forward(request, response);
	}
}