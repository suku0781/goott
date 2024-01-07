package com.miniPrj.service.member;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.miniPrj.controller.MemberFactory;
import com.miniPrj.service.MemberService;
import com.miniPrj.vo.Member;

public class ReadMemberInfoFromSes implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, MessagingException {
		resp.setContentType("application/json; charset=utf-8");

		System.out.println("test : "+req.getSession().toString()); 
		
		return null;
	}

}
