package com.jsp.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/jstl/sessionLogin3.do")
public class SessionLoginJstlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("나오는지 테스트");
		// 로그인 정보 읽기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id.equals("admin") && pw.equals("1234") ) {
			HttpSession ses = request.getSession();
			ses.setAttribute("id", id);

//			response.sendRedirect(pw);
			request.getRequestDispatcher("mainTest_jstl.jsp").forward(request, response);
		}
		
	}

}
