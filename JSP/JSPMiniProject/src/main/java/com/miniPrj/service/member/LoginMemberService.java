package com.miniPrj.service.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.miniPrj.controller.MemberFactory;
import com.miniPrj.dao.MemberCRUD;
import com.miniPrj.dao.MemberDAO;
import com.miniPrj.service.MemberService;
import com.miniPrj.vo.Member;

public class LoginMemberService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, MessagingException {
		String userId = req.getParameter("userId");
		String userPw = req.getParameter("userPw");
		MemberFactory mf = MemberFactory.getInstance();
		
		System.out.println("로그인하기");
		
		MemberDAO dao = MemberCRUD.getInstance();
		int result = -1;
		try {
			Member loginMember = dao.loginMember(userId, userPw);
			if(loginMember != null) {
				System.out.println(loginMember.toString());
				
				// member테이블에 포인트 update하고 pointlog에 기록 남기기
				result = dao.addPointToMember(userId, "로그인", 5);
				loginMember.setUserPoint(loginMember.getUserPoint() + 5);
				
				System.out.println("로그인 트랜잭션 결과 : "+result);
				
				HttpSession ses = req.getSession();
				ses.setAttribute("loginMember", loginMember); // 세션에 로그인 멤버 정보 바인딩
				
//				System.out.println("세션 확인 "+ ses.getAttribute("loginMember"));
//				req.getRequestDispatcher("../index.jsp").forward(req, resp);
				
				mf.setRedirect(true);
				mf.setWhereToGo(req.getContextPath() + "/index.jsp");
			} else { // 로그인 실패
				mf.setRedirect(true);
				mf.setWhereToGo("login.jsp?status?fail");
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
		return mf;
	}
	
}
