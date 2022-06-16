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

@WebServlet("/InsertMemberController")
public class InsertMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션생성
		HttpSession session = request.getSession();
		// 세션ID를 가져오기
		String sessionMemberId= (String) session.getAttribute("sessionMemberId");
		
		// 로그인 되어있는 상태라면 가계부페이지로 리다이렉트
		if(sessionMemberId != null) {	
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
			System.out.println("로그인 되어있음");
			return;
		} else {
			System.out.println("로그인 되어있지 않음");
		}
		
		// InsertMember.jsp로 포워딩
		request.getRequestDispatcher("/WEB-INF/view/InsertMember.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 매개변수값 받아오기
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberPw2 = request.getParameter("memberPw2");
		
		// 디버깅
		System.out.println("memberId: "+memberId);
		System.out.println("memberPw: "+memberPw);
		System.out.println("memberPw2: "+memberPw2);
		
		// 비밀번호와 비밀번호확인이 일치하지 않으면 회원가입페이지로 리다이렉트
		if(! memberPw.equals(memberPw2)) {
			response.sendRedirect(request.getContextPath()+"/InsertMemberController");
		}
		
		// Member객체생성하여 값저장
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// 회원가입메서드 적용
		MemberDao memberDao = new MemberDao();
		memberDao.insertMember(member);
		
		// 회원가입성공시 로그인페이지로 이동
		response.sendRedirect(request.getContextPath()+"/LoginController");
		
	}

}
