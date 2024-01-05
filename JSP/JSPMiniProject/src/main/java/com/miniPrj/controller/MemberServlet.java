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
		System.out.println("요청한 URL" + req.getRequestURL());
		System.out.println("요청한 URI" + req.getRequestURI());
		System.out.println("요청한 통신방식" + req.getMethod());
		System.out.println("컨택스트 패스" + req.getContextPath());
		
		// 요청된 서블릿 매핑주소를 통해서 기능을 분류
		String reqURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = reqURI.substring(contextPath.length());
		System.out.println("최종 요청된 서비스 : "+command);
		
		MemberFactory mf = MemberFactory.getInstance();
		MemberService service = mf.getService(command);
		
		try {
			service.executeService(req, resp);
		} catch (ServletException | IOException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("에러 메시지 :: "+e.getMessage());
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

}
