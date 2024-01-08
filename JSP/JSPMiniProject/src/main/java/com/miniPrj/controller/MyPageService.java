package com.miniPrj.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.dao.MemberCRUD;
import com.miniPrj.dao.MemberDAO;
import com.miniPrj.service.MemberService;
import com.miniPrj.vo.Member;
import com.miniPrj.vo.PointLog;

public class MyPageService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, MessagingException {
		// 멤버 정보 + pointlog정보
		String userId = req.getParameter("userId");
		
		MemberDAO dao = MemberCRUD.getInstance();
		
		try {
			Member memberInfo = dao.getMemberInfo(userId);
			List<PointLog> lst = dao.getPointLog(userId);
			
			req.setAttribute("memberInfo", memberInfo);
			req.setAttribute("pointLog", lst);
			
			req.getRequestDispatcher("myPage.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
