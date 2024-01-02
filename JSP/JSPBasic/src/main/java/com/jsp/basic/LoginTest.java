package com.jsp.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/loginTest.do", "/loginTest3", "/join"})
public class LoginTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("mainTest3.jsp?join");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		HttpSession ses = req.getSession();		
		PrintWriter out = resp.getWriter();
		
		if(id.equals("admin") && pw.equals("1234")) {
			ses.setAttribute("id", id);
			ses.setAttribute("pw", pw);
			
			resp.sendRedirect("mainTest3.jsp?loginSuccess");
		} else {
			out.print("에러발생!");
		}
		
		
	}

}
