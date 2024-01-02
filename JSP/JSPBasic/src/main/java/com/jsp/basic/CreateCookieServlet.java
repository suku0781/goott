package com.jsp.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet("/createCookie.do")
public class CreateCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie c = new Cookie("myCookie", "shk");
		
		c.setMaxAge(60*60*24); // 초단위 파라미터로 받는다. (하루동안 쿠키 저장)
		resp.addCookie(c); // 만들어진 쿠키 객체를 response객체에 담아 클라이언트로 전송
		
		System.out.println("쿠키 : "+c);
		
		// 현재의 sessionID값을 mySession이라는 이름의 쿠키로 저장
		Cookie sesId = new Cookie("mySession", req.getSession().getId());
		sesId.setMaxAge(7*24*60*60);
		resp.addCookie(sesId);
	}
	
	

}
