package com.jsp.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sendRedirect.do")
public class SendRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendRedirectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여까지옴");
		
		// id = abcd , pw = 1234
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		if(userId.equals("abcd") && userPw.equals("1234")) {
			System.out.println("로그인 성공.");
			out.print("<script>"
					+ "alert('로그인성공');"
					+ "</script>");
			
			response.sendRedirect("mainTest.jsp?status=loginSuccess&test=testCode");
		}
		
	}

}
