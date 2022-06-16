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

import dao.MemberDao;

@WebServlet("/SelectMemberOneController")
public class SelectMemberOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션생성
		HttpSession session = request.getSession();
		// 세션ID를 가져오기
		String sessionMemberId= (String) session.getAttribute("sessionMemberId");
		
		// 로그인 되어있지 않은 상태라면 로그인페이지로 리다이렉트
		if(sessionMemberId == null) {	
			response.sendRedirect(request.getContextPath()+"/LoginController");
			System.out.println("로그인 되어있지 않음");
			return;
		}
		
		String memberId= sessionMemberId;
		
		// 정보출력메서드 적용
		MemberDao memberDao = new MemberDao();
		List<Map<String, Object>> list = memberDao.selectMemberOne(memberId);
		
		// 디버깅
		for(Map m : list) {
			System.out.println("member정보: "+m);
		}
		
		// 넘길정보 담기
		request.setAttribute("list", list);
		
		// MemberOne.jsp로 포워딩
		request.getRequestDispatcher("/WEB-INF/view/MemberOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
