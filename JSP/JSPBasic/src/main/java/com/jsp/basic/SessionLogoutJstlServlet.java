package com.jsp.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/jstl/sessionLogin3.do")
public class SessionLogoutJstlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("나오는지 테스트");
		
		HttpSession ses = request.getSession();
		ses.removeAttribute("id");
		ses.invalidate();
		response.sendRedirect("mainTest_jstl.jsp");
	}

}
