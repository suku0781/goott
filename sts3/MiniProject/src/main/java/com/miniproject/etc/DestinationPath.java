package com.miniproject.etc;

import javax.servlet.http.HttpServletRequest;

/**
 * @package : com.miniproject.etc
 * @fileName : DestinationPath.java
 * @author : shk
 * @date  : 2024. 2. 1. 
 * @description : 경로를 세션에 기억시키는 클래스 
 */
public class DestinationPath {
	
	/**
	 * @MethodName : savePrePath
	 * @author : shk
	 * @date  : 2024. 2. 1. 
	 * @description : 갈제로 로그인하러 온 경우, 검사하는 곳에서 이전에 있던 경로를 넘기기위한 메서드 
	 * @returnType : void
	 * @param request
	 * @note : 
	 */
	public static void savePrePath(HttpServletRequest request) {
		String uri = request.getRequestURI();

		if(request.getQueryString() != null) { // 쿼리스트링이 있다면 
			uri = uri + "?" + request.getQueryString();
		}
		
		if(request.getMethod().equals("GET")) {
			// 세션에 저장
			request.getSession().setAttribute("returnPath", uri);
		}
		
	}
}
