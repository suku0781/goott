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
		System.out.println("GET방식으로 읽기");
		PrintWriter out = resp.getWriter();
		
		Cookie[] cookies = req.getCookies();
		for(Cookie c : cookies) {
			if( c.getName().equals("myCookie") ) {
//				out.print(c.getValue()); // myCookie 이름의 쿠키 값을 출력한다.
			}
			out.println(c.getName() + " : " + c.getValue() + "<br>");
			
		}
		
		
		// 쿠키 삭제 : 이름이 myCookie인 쿠키 삭제
		for(Cookie c : cookies) {
			if(c.getName().equals("myCookie") ) {
				c.setMaxAge(0); // 쿠키 만룍밧을 0으로 설정 -> 쿠키 삭제
				resp.addCookie(c); // 쿠키를 덞어써서 제거한다.
				break;
			}
		}
	}

}
