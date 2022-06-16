package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
	// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션생성
		HttpSession session = request.getSession();
		// 세션ID를 가져오기(null)
		String sessionMemberId = (String) session.getAttribute("sessionMemberId");
		
		// 로그인 되어있는 상태라면 가계부페이지로 리다이렉트
		if(sessionMemberId != null) {	
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
			System.out.println("로그인 되어있음");
			return;
		}
		
		// Login.jsp로 포워딩
		request.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
	
	}
		
	

	// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 뷰에서 submit한 Id와 Pw 받아오기
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// Member객체 생성후 담기
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// 모델호출
		MemberDao memberDao = new MemberDao();
		String sessionMemberId = memberDao.selectMemberByIdPw(member);
		
		// 로그인 실패시 로그인페이지로 sendRedirect(sendRedirect는 doGet방식)
		if(sessionMemberId == null) {
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		// 로그인 성공시 세션에 아이디 저장& 가계부페이지로 sendRedirect
		else {
			HttpSession session = request.getSession(); 
			session.setAttribute("sessionMemberId", sessionMemberId);
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
			return;
		}
	}

}
