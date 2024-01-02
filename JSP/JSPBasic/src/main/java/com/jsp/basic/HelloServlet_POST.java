package com.jsp.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet_POST")
public class HelloServlet_POST extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloServlet_POST() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("GET방식 호출됨.");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		System.out.println("POST방식 호출됨.");
		
		
		request.setCharacterEncoding("utf-8");
		

		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println("name : " + name);
		System.out.println("age : " + age);
		
		response.setContentType("text/html; charset=utf-8"); // 응답할 문서의 종류와 인코딩 방식 설정
		
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "<meta charset=\"UTF-8\">"
					+ "<title>Insert title here</title>"
					+ "</head>"
					+ "<body>"
					+ "<h1>"+name+"</h1>"
					+ "<h1>"+age+"</h1>"
					+ "</body>"
					+ "</html>");
		
		out.flush();
		out.close();
	}

}


