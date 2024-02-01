package com.miniproject.etc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCheck implements HttpSessionListener {

	private static Map<String, HttpSession> sessions = new ConcurrentHashMap<String, HttpSession>();
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// 세선이 생성되면 동작
		System.out.println("세션 생성됨.");
		System.out.println("생성된 세션ID : " + se.getSession().getId());

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("세션 종료됨." + se.getSession().getId());

	}

}
