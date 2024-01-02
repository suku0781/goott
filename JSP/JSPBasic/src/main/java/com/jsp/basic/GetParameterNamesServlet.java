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
			// ������Ұ� �ִٸ� �ݺ�
			String[] valueArr = request.getParameterValues(el);
			
			// �Ϲ� �迭String[]�� arrayList�� ��ƺ���
			List<String> valueList = new ArrayList<String>();
			
			// ���1) �ݺ������� �ϳ��� add
			for(String value : valueArr) {
				valueList.add(value);
			}
			
			System.out.println(el + ": " + Arrays.asList(valueArr)); // ���2)
			
			// map�� name���� values�� �ֱ�(Enumeration)
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
		
		// ���Ͱ��� Enumeration�� Ȱ���ؼ� map�� �����ϰ� ����� �� �ְ� �Ǵ� getParameter()�� �̿��� �� �ִ�. 
		// Enumeration �� �������̽��̹Ƿ� �߻����̰�, �����ϰ� �����ؼ� �����ִ�. 
		// Front���� �ٲ� parameter name�� �ٱʹ���, ����� �� �ִ�.
	}

}
