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
		
		System.out.println("���̵� �ߺ��˻� �ؾ��Ѵ�.");
		String userId = req.getParameter("userId");
		MemberDAO dao = MemberCRUD.getInstance();
		Map<String, String> jsonMap = new HashMap<>();
		SimpleDateFormat smt = new SimpleDateFormat("yyyy�� MM�� dd�� HH:mm:ss");
		PrintWriter out = resp.getWriter();
		try {
			Member mem = dao.duplicateUserId(userId);
			
//			System.out.println("���"+mem.toString()); 
			
			if(mem != null) { // ���̵� �ߺ��� ���
				jsonMap.put("isDuplicate", "true");
			} else { // ���̵� �ߺ��� �ƴ� ���
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
