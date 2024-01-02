package com.jsp.practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginPractice2.do")
public class LoginPractice2 extends HttpServlet {
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String birthAndGender = request.getParameter("birthAndGender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		if(id.equals("admin") && pw.equals("1234")) {
			HttpSession ses = request.getSession();
			ses.setAttribute("userId", "admin");
			
//			request.sendRedirect("loginPracticeResult.jsp");
			request.getRequestDispatcher("loginPracticeResult.jsp").forward(request, response);
		}
	
	}

	
}
