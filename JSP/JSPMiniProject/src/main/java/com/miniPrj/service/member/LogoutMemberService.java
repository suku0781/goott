package com.miniPrj.service.member;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.miniPrj.controller.MemberFactory;
import com.miniPrj.service.MemberService;

public class LogoutMemberService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, MessagingException {
		HttpSession ses = req.getSession();
		MemberFactory mf = MemberFactory.getInstance();
		System.out.println("로그아웃 하기");
		ses.removeAttribute("loginMember"); // 세션에 바인딩한 로그인 멤버 정보 삭제
		
		mf.setRedirect(true);
		mf.setWhereToGo("../index.jsp");
		
//		req.getRequestDispatcher("../index.jsp").forward(req, resp);
		
		return mf;
	}

}
