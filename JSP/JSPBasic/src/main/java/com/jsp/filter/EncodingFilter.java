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
	public void destroy() { // ���Ͱ�ü�� ���ŵɶ� ����Ǵ� �޼���
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("���� ������...");
		
		request.setCharacterEncoding(CharSet);
		response.setContentType("text/html; charset="+CharSet);
		
		chain.doFilter(request, response); /* ���� ü�� ���� */
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	

}
