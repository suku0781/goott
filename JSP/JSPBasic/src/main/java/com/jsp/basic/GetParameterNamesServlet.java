package com.jsp.basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getParamNames.do")
public class GetParameterNamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetParameterNamesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> params = request.getParameterNames();
		Map<String, List<String>> pMap = new HashMap();
		
		while(params.hasMoreElements()) {
			String el = params.nextElement();
			// 다음요소가 있다면 반복
			String[] valueArr = request.getParameterValues(el);
			
			// 일반 배열String[]을 arrayList에 담아보기
			List<String> valueList = new ArrayList<String>();
			
			// 방법1) 반복문으로 하나씩 add
			for(String value : valueArr) {
				valueList.add(value);
			}
			
			System.out.println(el + ": " + Arrays.asList(valueArr)); // 방법2)
			
			// map에 name값과 values값 넣기(Enumeration)
			pMap.put(el, valueList);
			
		} // end of while
		
		String userIdStr = "";
		if(pMap.containsKey("userId")) {
			List<String> valueUserId = pMap.get("userID");
			userIdStr = valueUserId.get(0);
		}
		
		
		// Map 
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> tmpSet =  map.entrySet();
		for(Entry<String, String[]> entry : tmpSet ) {
			System.out.println(entry.getKey() + " : " + Arrays.toString(entry.getValue()));
		}
		
		// 위와같이 Enumeration을 활용해서 map을 생성하고 출력할 수 있고 또는 getParameter()를 이용할 수 있다. 
		// Enumeration 은 인터페이스미므로 추상적이고, 유연하게 구현해서 쓸수있다. 
		// Front단이 바뀌어서 parameter name이 바귀더라도, 사용할 수 있다.
	}

}
