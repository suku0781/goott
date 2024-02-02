package com.miniproject.interceptor;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.miniproject.domain.Member;
import com.miniproject.domain.Session;
import com.miniproject.etc.SessionCheck;
import com.miniproject.service.member.MemberService;

// 제어를 빼앗아 실제 로그인을 처리하는 interceptor
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	private MemberService mService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("loginInterceptor - preHandle() : 로그인 처리하러 왔다 : " + handler);
		
		// 댓글 작성 로그인(이전 경로 저장)처리
		if(request.getMethod().equals("GET") && request.getParameter("redirectURL") != null) { // GET방식, 쿼리스트링에 redirectURL이 존재하는 경우
			if(!request.getParameter("redirectURL").equals("") && request.getParameter("redirectURL").contains("viewBoard")) {
				String uri = "/board/viewBoard";
				String queryString = "?no=" + request.getParameter("no");
				
				request.getSession().setAttribute("returnPath", uri + queryString);
			}
		}
	
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
			
			// 자동 로그인 처리
			// 자동 로그인 체크 유저일 경우 (on 이 전달될 경우)
//			-> 쿠키 생성. (loginCookie=sessionId)
//			-> DB member 테이블에 sessionLimit, sessionKey를 저장.
			if(request.getParameter("autoLogin") != null) {
				System.out.println("자동로그인을 체크한 유저임.");
//				1. 쿠키 생성 : LoginCookie = sessionId, 만료일 : 7일
				String sessionValue = ses.getId();
				Timestamp sesLimit = new Timestamp(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)); // 쿠키 만료일
				Cookie cookie = new Cookie("loginCookie", sessionValue); // 쿠키 객체 생성.
				cookie.setMaxAge(7 * 24 * 60 * 60);
				cookie.setPath("/");
				
				System.out.println("loginMember : " + loginMember.toString());
				System.out.println("loginMember.getUserId() : " + loginMember.getUserId());
				System.out.println("만들어진 session : " + new Session(loginMember.getUserId(), sesLimit, sessionValue));
				
				if(mService.remember(new Session(loginMember.getUserId(), sesLimit, sessionValue))) {
					response.addCookie(cookie); // 쿠키 저장
					System.out.println("쿠기 저장됨.");
				}
				
			}
			
			// 로그인 성공 후 돌아갈 경로 처리
			String returnPath = "";
			if(ses.getAttribute("returnPath") != null) {
				returnPath = (String) ses.getAttribute("returnPath");
			}
			System.out.println("returnPath : " + returnPath);
			
			response.sendRedirect(!returnPath.equals("") ? returnPath : "/");
		}
		
	}

}
