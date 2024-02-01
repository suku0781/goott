package com.miniproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.miniproject.domain.Member;
import com.miniproject.etc.SessionCheck;

// 제어를 빼앗아 실제 로그인을 처리하는 interceptor
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("loginInterceptor - preHandle() : 로그인 처리하러 왔다 : " + handler);
	
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("loginInterceptor - postHandle() : 로그인 처리됨.");		
		
		HttpSession ses = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		Member loginMember = (Member) modelMap.get("loginMember");
		
		if(loginMember != null) { // 로그인 성공 시 세션에 로그인 기록을 남긴다.
			System.out.println(loginMember.getUserId() + " 로그인 성공!");
			ses.setAttribute("loginMember", loginMember);
			
			// 중복 로그인 체크
			SessionCheck.replaceSessionKey(ses, loginMember.getUserId());
			
			// 로그인 성공 후 돌아갈 경로 처리
			String returnPath = "";
			if(ses.getAttribute("returnPath") != null) {
				returnPath = (String) ses.getAttribute("returnPath");
			}
			
			System.out.println("보고싶은 리턴 패스는 " + returnPath);
			
			response.sendRedirect(!returnPath.equals("") ? returnPath : "/");
		}
		
	}

}
