package com.miniproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean result = false;
		
		System.out.println("로그인 했는지 안했는지 검사하기. - AuthenticationInterceptor preHandler() : ");
		
		HttpSession ses = request.getSession();
		
		if(ses.getAttribute("loginMember") != null) {
			System.out.println("로그인 됨.");
			result = true;
		} else {
			System.out.println("로그인 안됨.");
			response.sendRedirect("/member/login");
		}
		
		return result;
	}
}
