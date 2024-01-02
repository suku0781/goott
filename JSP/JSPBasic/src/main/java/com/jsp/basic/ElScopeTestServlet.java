package com.jsp.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberDTO;

@WebServlet("/elScope.do")
public class ElScopeTestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num1 = 1, num2 = 3;
		int result = num1 - num2;
		
		MemberDTO member = new MemberDTO("test01", "1234", null, null, 20, "010-1111-1111", "male", null, null, null);
		
		req.setAttribute("member", member); // request영역에 바인딩
		
		HttpSession ses = req.getSession();
		ses.setAttribute("result", result);
		req.getRequestDispatcher("elScope.jsp").forward(req, resp);
	}
}
