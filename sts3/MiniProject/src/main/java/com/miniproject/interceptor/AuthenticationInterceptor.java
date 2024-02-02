package com.miniproject.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.miniproject.domain.Member;
import com.miniproject.etc.DestinationPath;
import com.miniproject.service.board.BoardService;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	@Inject
	private BoardService bService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean result = false;
		
		System.out.println("로그인 했는지 안했는지 검사하기. - AuthenticationInterceptor preHandler() : ");
		
		DestinationPath.savePrePath(request);
		HttpSession ses = request.getSession();
		
		if(ses.getAttribute("loginMember") != null) {
			System.out.println("로그인 됨.");
			result = true;
			
			String uri = request.getRequestURI();
			
			// writter 정보를 쿼리스트링이 아닌 service -> dao 거져서 writter정보를 가져오는 방법
			String writter = bService.getBoardWritter( Integer.parseInt(request.getParameter("no")) );
			
			// 수정/삭제의 경우 로그인 userId -- 작성자writer 조건을 만족해야함.
//			String writter = request.getParameter("writter");
			Member loginMember = (Member)ses.getAttribute("loginMember");
			System.out.println("writter : "+writter);
			
			if(uri.contains("editBoard") || uri.contains("deleteBoard")) { // 쿼리스트링에 editBoard가 포함되어있을 경우(수정/삭제 경우)
				if(!loginMember.getUserId().equals(writter)) {
					response.sendRedirect("viewBoard?no=" + request.getParameter("no") + "&writter="+writter + "&status=noPermission");
					result = false;
				}
			}
			
			
		} else {
			System.out.println("로그인 안됨.");
			response.sendRedirect("/member/login");
		}
		
		return result;
	}
}
