package com.jsp.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogout.do")
public class SessionLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SessionLogout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �α׾ƿ� ó�� : session��ü�� �����ִ� �α��� ������ ���� �� ��ȿȭ
		HttpSession ses = request.getSession();
		ses.removeAttribute("loginMemberId");
		ses.invalidate(); // ���� ��ȿȭ -> ���� ����.
		response.sendRedirect("mainTest.jsp");		
	}

}