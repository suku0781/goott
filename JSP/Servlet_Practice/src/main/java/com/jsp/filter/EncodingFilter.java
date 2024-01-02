package com.jsp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodingFilter implements Filter {
	private String CharSet = "UTF-8";

	@Override
	public void destroy() { // 필터객체가 제거될때 실행되는 메서드
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("필터 동작중...");
		
		request.setCharacterEncoding(CharSet);
		response.setContentType("text/html; charset="+CharSet);
		
		chain.doFilter(request, response); /* 필터 체인 연결 */
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	

}
