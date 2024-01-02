package com.jsp.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ex3_scoreVallidation")
public class Ex3_scoreVallidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex3_scoreVallidation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		PrintWriter out = response.getWriter();
		
		// �������� ��ȿ�� �˻�
		if(kor < 0 || kor > 100) {
			out.print("<script>");
			out.print("alert('���������� ��ȿ���� �ʽ��ϴ�.');");
			out.print("location.href='callServletAsGet.jsp';");
			out.print("</script>");
		}
		
		out.print("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "<title>Insert title here</title>"
				+ "</head>"
				+ "<body>"
				+ "<p>���� : "+kor+"</p>"
				+ "<p>���� : "+eng+"</p>"
				+ "<p>���� : "+math+"</p>"
				+ "<p>���� : "+(kor + eng + math)+"</p>"
				+ "<p>��� : "+((kor + eng + math)/3f)+"</p>"
				+ "</body>"
				+ "</html>");
		out.flush();
		out.close();
		
	}

}
