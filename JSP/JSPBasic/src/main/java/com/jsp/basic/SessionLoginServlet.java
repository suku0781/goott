package com.jsp.basic;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SessionLoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		if(userId.equals("abcd") && userPw.equals("1234")) {
			// �α��� ���� - ���ǰ�ü�� �α��� ������ �����.
			HttpSession ses = request.getSession();
			System.out.println("���� id : " + ses.getId());
			
			ses.setAttribute("loginMemberId", userId); // ���ε�
			ses.setAttribute("loginMemberPw", userPw); // ���ε�
			
			
			response.sendRedirect("mainTest.jsp?status=loginSuccess");
		}
		
	}

}
