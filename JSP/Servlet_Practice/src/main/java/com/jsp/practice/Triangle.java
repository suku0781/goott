package com.jsp.practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Triangle")
public class Triangle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Triangle() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int base = Integer.parseInt(request.getParameter("base")); 
		int height = Integer.parseInt(request.getParameter("height"));
		response.setContentType("text/html; charset=utf-8"); // 한글이 들어갈 경우 
		PrintWriter out = response.getWriter();

		out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "<meta charset=\"UTF-8\">"
					+ "<title>Insert title here</title>"
					+ "</head>"
					+ "<body>"
					+ "<p>base : "+base+"</p>"
					+ "<p>height : "+height+"</p>"
					+ "<p>area : "+((base*height)/2f)+"</p>"
					+ "</body>"
					+ "</html>");
		out.flush();
		out.close();
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name"); 
		String id = request.getParameter("id"); 
		String pw = request.getParameter("pw"); 
		
		response.setContentType("text/html; charset=utf-8"); // 응답할 문서의 종류와 인코딩 방식 설정
		
		PrintWriter out = response.getWriter();

		out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "<meta charset=\"UTF-8\">"
					+ "<title>result</title>"
					+ "</head>"
					+ "<body>"
					+ "<p>이름 : "+name+" </p>"
					+ "<p>id : "+id+" </p>"
					+ "<p>pw : "+pw+" </p>"
					+ "</body>"
					+ "</html>");
		out.flush();
		out.close();
	}

}
