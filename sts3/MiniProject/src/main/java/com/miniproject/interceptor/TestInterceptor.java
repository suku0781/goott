package com.miniproject.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("testInterceptor - preHandle()이 호출됨.");
		
		HandlerMethod method = (HandlerMethod)handler;
		Method m = method.getMethod();
		
		System.out.println("bean : " + method.getBean());
		System.out.println("method : " + m);
		
		return true; // true : 원래 controller단 호출, false : 원래 controller단 호출하지 않는다. 
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		System.out.println("testInterceptor - postHandle()이 호출됨.");
		System.out.println("result : " + modelAndView.getModel().get("result").toString());
		Object result = modelAndView.getModel().get("result");
		if(result != null) {
			request.getSession().setAttribute("result", result);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		System.out.println("testInterceptor - afterCompletion()이 호출됨.");
	}
	

}
