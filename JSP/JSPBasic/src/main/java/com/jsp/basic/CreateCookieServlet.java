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
		
		c.setMaxAge(60*60*24); // �ʴ��� �Ķ���ͷ� �޴´�. (�Ϸ絿�� ��Ű ����)
		resp.addCookie(c); // ������� ��Ű ��ü�� response��ü�� ��� Ŭ���̾�Ʈ�� ����
		
		System.out.println("��Ű : "+c);
		
		// ������ sessionID���� mySession�̶�� �̸��� ��Ű�� ����
		Cookie sesId = new Cookie("mySession", req.getSession().getId());
		sesId.setMaxAge(7*24*60*60);
		resp.addCookie(sesId);
	}
	
	

}
