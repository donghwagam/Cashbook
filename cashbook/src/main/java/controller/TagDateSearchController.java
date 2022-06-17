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

import dao.HashtagDao;

@WebServlet("/TagDateSearchController")
public class TagDateSearchController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션생성
		HttpSession session = request.getSession();
		// 세션ID를 가져오기(null)
		String sessionMemberId = (String) session.getAttribute("sessionMemberId");
		
		// 로그인이 하지 않은 상태라면 
		if(sessionMemberId == null) {	
			response.sendRedirect(request.getContextPath()+"/LoginController");
			System.out.println("로그인 되어있음");
			return;
		}
		
		String memberId = sessionMemberId;
		
		if(request.getParameter("cashDate") != null) {
			String cashDate = request.getParameter("cashDate");
			HashtagDao hashbookDao = new HashtagDao();
			List<Map<String, Object>> list = hashbookDao.tagDateSearchList(cashDate , memberId);
			request.setAttribute("cashDate", cashDate);
			request.setAttribute("list", list);
		}
		request.getRequestDispatcher("/WEB-INF/view/TagDateSearch.jsp").forward(request, response);
	}
}