package com.miniPrj.service.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.miniPrj.controller.MemberFactory;
import com.miniPrj.dao.MemberCRUD;
import com.miniPrj.dao.MemberDAO;
import com.miniPrj.service.MemberService;
import com.miniPrj.vo.Member;

public class DuplicateUserIdService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("application/json; charset=utf-8");
		
		System.out.println("아이디 중복검사 해야한다.");
		String userId = req.getParameter("userId");
		MemberDAO dao = MemberCRUD.getInstance();
		Map<String, String> jsonMap = new HashMap<>();
		SimpleDateFormat smt = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
		PrintWriter out = resp.getWriter();
		try {
			Member mem = dao.duplicateUserId(userId);
			
//			System.out.println("고맴"+mem.toString()); 
			
			if(mem != null) { // 아이디가 중복일 경우
				jsonMap.put("isDuplicate", "true");
			} else { // 아이디가 중복이 아닌 경우
				jsonMap.put("isDuplicate", "false");
			}
			jsonMap.put("target", "userIdDuplChk");
			jsonMap.put("status", "success");
		    jsonMap.put("outputDate", smt.format(Calendar.getInstance().getTime()));
		} catch (NamingException | SQLException e) {
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
