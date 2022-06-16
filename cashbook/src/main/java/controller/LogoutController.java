package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기존의 세션을 종료시킨다.
		request.getSession().invalidate();
		
		// 세션이 종료되면 LoginController로 리다이렉트
		response.sendRedirect(request.getContextPath()+"/LoginController");
	}

}
