package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;

@WebServlet("/DeleteMemberController")
public class DeleteMemberController extends HttpServlet {
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
		
		// DeleteMember.jsp로 포워딩
		request.getRequestDispatcher("/WEB-INF/view/DeleteMember.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		// 매개변수값 받아오기
		String memberPw = request.getParameter("memberPw");
		
		// 디버깅
		System.out.println("memberPw: "+memberPw);
		
		// 회원탈퇴메서드 적용
		MemberDao memberDao = new MemberDao();
		memberDao.delelteMember(memberId, memberPw);
		
		
		// 세션삭제
		session.invalidate();
		
		// 회원탈퇴성공시 로그인페이지로 이동
		response.sendRedirect(request.getContextPath()+"/LoginController");
	}

}
