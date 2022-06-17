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
// 1.(Model)dao에서 만든 메서드를 이용해서
// 2.(Controller)a태그로 창을 받으면 doGet, form태그로 창을 받으면 doPost / setAttribute이용해서 list넘길거임. Dispatcher이용해서 view단으로 넘김
// 3.(View) getAttribute이용해서 list받아줌. 

@WebServlet("/TagController")
public class TagController extends HttpServlet {
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
		
		HashtagDao hashtagDao = new HashtagDao();
		
		List<Map<String,Object>> list = hashtagDao.selectTagRankList(sessionMemberId);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/TagList.jsp").forward(request, response);
	}
}