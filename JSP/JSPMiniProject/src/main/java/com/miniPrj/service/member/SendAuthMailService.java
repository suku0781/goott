package com.miniPrj.service.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
//import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.miniPrj.controller.MemberFactory;
import com.miniPrj.etc.SendMail;
import com.miniPrj.service.MemberService;

public class SendAuthMailService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("application/json; charset=utf-8");
		
		String userEmail = req.getParameter("userEmail");
		
		
		PrintWriter out = resp.getWriter();
		
		Map<String, String> jsonMap = new HashMap<>();
		
		System.out.println(userEmail+"�� ���� ����.");
		
		// �����ڵ带 ����� �ڵ带 ���ǿ� ����
		String code = UUID.randomUUID().toString();
		System.out.println(code);
		
		req.getSession().setAttribute("authCode", code); // ���ǿ� ����.
		
		// ���� �߼�.
		try {
			SendMail.sendMail(userEmail, code);
			jsonMap.put("status", "success");
			jsonMap.put("target", "authCode");
			jsonMap.put("code", code);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonMap.put("status", "fail");
			jsonMap.put("errorMessage", e.getMessage());
		}
		
		JSONObject json = new JSONObject(jsonMap);
		
		out.print(json.toJSONString());
		
		out.flush();
		out.close();
		
		return null;
	}

}
