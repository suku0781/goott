package com.jsp.basic;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/readCookie.do")
public class ReadCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET������� �б�");
		PrintWriter out = resp.getWriter();
		
		Cookie[] cookies = req.getCookies();
		for(Cookie c : cookies) {
			if( c.getName().equals("myCookie") ) {
//				out.print(c.getValue()); // myCookie �̸��� ��Ű ���� ����Ѵ�.
			}
			out.println(c.getName() + " : " + c.getValue() + "<br>");
			
		}
		
		
		// ��Ű ���� : �̸��� myCookie�� ��Ű ����
		for(Cookie c : cookies) {
			if(c.getName().equals("myCookie") ) {
				c.setMaxAge(0); // ��Ű �������� 0���� ���� -> ��Ű ����
				resp.addCookie(c); // ��Ű�� ����Ἥ �����Ѵ�.
				break;
			}
		}
	}

}
