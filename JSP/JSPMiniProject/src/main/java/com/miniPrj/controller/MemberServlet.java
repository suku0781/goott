package com.miniPrj.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.service.MemberService;

@WebServlet("*.mem")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = -4014011767649223251L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
	
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("��û�� URL" + req.getRequestURL());
		System.out.println("��û�� URI" + req.getRequestURI());
		System.out.println("��û�� ��Ź��" + req.getMethod());
		System.out.println("���ý�Ʈ �н�" + req.getContextPath());
		
		// ��û�� ���� �����ּҸ� ���ؼ� ����� �з�
		String reqURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = reqURI.substring(contextPath.length());
		System.out.println("���� ��û�� ���� : "+command);
		
		MemberFactory mf = MemberFactory.getInstance();
		MemberService service = mf.getService(command);
		
		try {
			service.executeService(req, resp);
		} catch (ServletException | IOException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���� �޽��� :: "+e.getMessage());
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

}
