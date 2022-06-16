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

@WebServlet("/UpdateMemberPwController")
public class UpdateMemberPwController extends HttpServlet {

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
		
		// UpdateMemberPw.jsp로 포워딩
		request.getRequestDispatcher("/WEB-INF/view/UpdateMemberPw.jsp").forward(request, response);
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
		String memberPw2 = request.getParameter("memberPw2");
		
		// 디버깅
		System.out.println("세션 아이디: "+ memberId);
		System.out.println("기존 비밀번호: "+memberPw);
		System.out.println("변경 비밀번호: "+memberPw2);
		
		// 비밀번호변경메서드 적용
		MemberDao memberDao = new MemberDao();
		int row = memberDao.updateMemberPw(memberId, memberPw, memberPw2);
		if(row == 1) {
			System.out.println("비밀번호 변경 성공");
		} else {
			System.out.println("비밀번호 변경 실패");
		}
		
		// 세션 종료
		session.invalidate();
		
		// 비밀번호변경성공시 로그인페이지로 이동
		response.sendRedirect(request.getContextPath()+"/LoginController");
	}

}
