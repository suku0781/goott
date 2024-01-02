package com.jsp.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PageMove.do")
public class PageMovingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PageMovingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ������ �̵� ���
		// 1. js location.href�� �̿�
		PrintWriter out = response.getWriter();
		
//		out.print("<script>"
//				+ "location.href='mainTest.jsp'"
//				+ "</script>");

		// 2. sendRedirect() �޼��� �̿�
//		response.sendRedirect("mainTest.jsp");
		
		// 3. requestDispatchar��ü�� �̿�
		// �����͸� ���ε��Ͽ� ������ ���
		// �ּҰ� �ٲ��� ����.
//		request.getRequestDispatcher("mainTest.jsp").forward(request, response);  
		
		// 4. meta refresh 
		// 5�� �� mainTest.jsp�� �̵��Ѵ�. 
		// �� ������� �ʴ� ����̴�. 
		out.println("<html>"
				+ "<head>"
				+ "<meta http-equiv='refresh' content='5; URL=mainTest.jsp'>"
				+ "</head>"
				+ "</html>");
		
		
		
	}


}
