package com.miniproject.etc;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCheck implements HttpSessionListener {

	private static Map<String, HttpSession> sessions = new ConcurrentHashMap<String, HttpSession>();
	
	@Override
	public synchronized void sessionCreated(HttpSessionEvent se) {
		System.out.println("세션 생성됨.");
		System.out.println("생성된 세션ID : " + se.getSession().getId());

		// 세선이 생성되면 Map에 해당 세션 등록
		sessions.put(se.getSession().getId(), se.getSession());	
		printSessionsMap();
		
	}
	
	@Override
	public synchronized void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("세션 종료됨." + se.getSession().getId());
		// 세션이 종료되면 Map에서도 해당 세션 삭제
		if(sessions.containsKey(se.getSession().getId())) {
			se.getSession().invalidate();
			sessions.remove(se.getSession().getId());
		}
	}
	
	/**
	 * @MethodName : replaceSessionKey
	 * @author : shk
	 * @date  : 2024. 2. 1. 
	 * @description : 세션객체의 아이디를 userId로 바꾸기위함.
	 * @returnType : void
	 * @param ses
	 * @param loginUserId
	 * @note : 세션 리스트에 로그인 유저 아이디가 없고 ses 값만 있을 경우 최초 로그인으로 판별 
	 */
	public static synchronized void replaceSessionKey(HttpSession ses, String loginUserId) {
		if(!sessions.containsKey(loginUserId) && sessions.containsValue(ses)) {
			System.out.println(loginUserId + "로 최초 로그인.");
			
			sessions.put(loginUserId, ses);
			sessions.remove(ses.getId());
		} else if(sessions.containsKey(loginUserId)){
			System.out.println(loginUserId + "로 중복 로그인 시도가 감지됨.");
			
			removeKey(loginUserId);
			sessions.put(loginUserId, ses);
		}
		
		printSessionsMap();
	}
	
	/**
	 * @MethodName : removeKey
	 * @author : shk
	 * @date  : 2024. 2. 1. 
	 * @description : 기존 userId의 세션이 존재할 경우 기존의 세션을 지움.
	 * @returnType : void
	 * @param userId
	 * @note : 
	 */
	public static void removeKey(String userId) {
		if(sessions.containsKey(userId)) {
			sessions.get(userId).removeAttribute("loginMember");
			
			sessions.get(userId).invalidate(); // 세션 value 값 무효화.(로그아웃)
			sessions.remove(userId);
		}
		
		printSessionsMap();
	}
	

	/**
	 * @MethodName : printSessionsMap
	 * @author : shk
	 * @date  : 2024. 2. 1. 
	 * @description : console에 세션리스트 출력
	 * @returnType : void
	 * @note : 
	 */
	private static void printSessionsMap() {
		System.out.println("현재 생성된 세션 리스트-------------------------");
		Set<String> keys = sessions.keySet();
		
		for(String key : keys) {
			System.out.println(key + " : " + sessions.get(key).toString());
		}
		System.out.println("--------------------------------------------------");
		
	}
}
