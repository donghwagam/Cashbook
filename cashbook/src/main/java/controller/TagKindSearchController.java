package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HashtagDao;

@WebServlet("/TagKindSearchController")
public class TagKindSearchController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
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
		
		if(request.getParameter("kind") != null) {
			String kind = request.getParameter("kind");
			HashtagDao hashtagDao = new HashtagDao();
			List<Map<String, Object>> list = hashtagDao.tagKindSearchList(kind, memberId);
			request.setAttribute("kind", kind);
			request.setAttribute("memberId", memberId);
			request.setAttribute("list", list);
			
			System.out.println("[kind TagKindSearchController] : " + kind);
			System.out.println("[memberId TagKindSearchController] : " + memberId);
		}
		
		request.getRequestDispatcher("/WEB-INF/view/TagKindSearch.jsp").forward(request, response);
	}
}