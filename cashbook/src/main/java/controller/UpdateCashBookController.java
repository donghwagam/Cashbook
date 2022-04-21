package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;


@WebServlet("/UpdateCashBookController")
public class UpdateCashBookController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 정보 받아오기
		int cashbookNo = 0;
		if(request.getParameter("cashbookNo") != null) {
			cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		}
		
		String cashDate = null;
		if(request.getParameter("cashDate") != null) {
			cashDate = request.getParameter("cashDate");
		}
		
		String kind = null;
		if(request.getParameter("kind") != null) {
			kind = request.getParameter("kind");
		}
		
		int cash = 0;
		if(request.getParameter("cash") != null) {
			cash = Integer.parseInt(request.getParameter("cash"));
		}
		
		String memo = null;
		if(request.getParameter("memo") != null) {
			memo = request.getParameter("memo");
		}
		
		request.getRequestDispatcher("/WEB-INF/view/UpdateCashBook.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 정보 받기
		int cashbookNo = (Integer)request.getAttribute("cashbookNo");
		
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
		
		// 수정 구현
		CashbookDao cashbookDao = new CashbookDao();
		int row = cashbookDao.updateCashbook(null, null, cash);
	}

}
