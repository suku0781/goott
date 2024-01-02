package com.jsp.practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginPractice")
public class LoginPractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginPractice() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
//		System.out.println("test!" + userId);
//		Map<String, String> arr = new HashMap<>();
//		arr.put(userId, userPw);
		
		if(userId.equals("admin") && userPw.equals("1234")) {
			HttpSession ses = request.getSession();
			ses.setAttribute("userId", userId);
			
			response.sendRedirect("loginPractice.jsp");
		}
		
	}

}
