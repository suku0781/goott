package com.jsp.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CallServlet_GET")
public class CallServlet_GET extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CallServlet_GET() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int kor= Integer.parseInt(request.getParameter("kor"));
		int eng= Integer.parseInt(request.getParameter("eng"));
		int math= Integer.parseInt(request.getParameter("math"));
		
		System.out.println("GET������� ȣ��� : " + kor + ", " + eng + ", " + math);
		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		
//		
//		out.print("<!DOCTYPE html>"
//				+ "<html>"
//				+ "<head>"
//				+ "<meta charset=\"UTF-8\">"
//				+ "<title>Insert title here</title>"
//				+ "</head>"
//				+ "<body>"
//				+ "<p>���� : "+kor+"</p>"
//				+ "<p>���� : "+eng+"</p>"
//				+ "<p>���� : "+math+"</p>"
//				+ "<p>���� : "+(kor + eng + math)+"</p>"
//				+ "<p>��� : "+((kor + eng + math)/3f)+"</p>"
//				+ "</body>"
//				+ "</html>");
//		out.flush();
//		out.close();
		
		// ��¹��2
		// ����� ���� ���ε�(binding : �����͸� ������. )
		request.setAttribute("kor", kor);
		request.setAttribute("eng", eng);
		request.setAttribute("math", math);
		request.setAttribute("tot", (kor + eng + math));
		request.setAttribute("avg", ((kor + eng + math)/3f));
		
		// ���� ~ ��������
		RequestDispatcher rd = request.getRequestDispatcher("dataOutputFromServlet.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
