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
		// 페이지 이동 방법
		// 1. js location.href를 이용
		PrintWriter out = response.getWriter();
		
//		out.print("<script>"
//				+ "location.href='mainTest.jsp'"
//				+ "</script>");

		// 2. sendRedirect() 메서드 이용
//		response.sendRedirect("mainTest.jsp");
		
		// 3. requestDispatchar객체를 이용
		// 데이터를 바인딩하여 보낼때 사용
		// 주소가 바뀌지 않음.
//		request.getRequestDispatcher("mainTest.jsp").forward(request, response);  
		
		// 4. meta refresh 
		// 5초 후 mainTest.jsp로 이동한다. 
		// 잘 사용하지 않는 방식이다. 
		out.println("<html>"
				+ "<head>"
				+ "<meta http-equiv='refresh' content='5; URL=mainTest.jsp'>"
				+ "</head>"
				+ "</html>");
		
		
		
	}


}
