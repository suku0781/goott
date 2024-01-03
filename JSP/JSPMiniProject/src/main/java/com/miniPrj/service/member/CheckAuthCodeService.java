package com.miniPrj.service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.miniPrj.controller.MemberFactory;
import com.miniPrj.service.MemberService;

public class CheckAuthCodeService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, MessagingException {
		resp.setContentType("application/json; charset=utf-8");
		String userEmailAuthInp = req.getParameter("userEmailAuthInp"); // 유저가 입력한 인증 코드 
		String authCode = (String) req.getSession().getAttribute("authCode");
		PrintWriter out = resp.getWriter();
		JSONObject json = new JSONObject();
		
		json.put("target", "checkAuthCode");
		if(userEmailAuthInp.equals(authCode)) {
			json.put("activation", "success");
		} else {
			json.put("activation", "fail");
			
		}
		out.print(json.toJSONString());
		out.flush();
		out.close();
		
		return null;
	}

}
