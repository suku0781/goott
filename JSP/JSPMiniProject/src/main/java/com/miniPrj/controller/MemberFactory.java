package com.miniPrj.controller;

import com.miniPrj.service.MemberService;
import com.miniPrj.service.member.CheckAuthCodeService;
import com.miniPrj.service.member.DuplicateUserEmailService;
import com.miniPrj.service.member.DuplicateUserIdService;
import com.miniPrj.service.member.LoginMemberService;
import com.miniPrj.service.member.LogoutMemberService;
import com.miniPrj.service.member.ReadMemberInfoFromSes;
import com.miniPrj.service.member.RegisterMemberService;
import com.miniPrj.service.member.SendAuthMailService;

public class MemberFactory {
	private boolean redirect;
	private String whereToGo;
	private static MemberFactory instance = null;
	
	private MemberFactory() {}
	
	public static MemberFactory getInstance() {
		if(instance == null) instance = new MemberFactory();
		return instance;
	}

	// 서비스 객체를 만들어주는것이 이 클래스에서 진행됨.
	public MemberService getService(String command) {
		MemberService result = null;
		System.out.println("command : "+command);
		if(command.equals("/member/dulpUserId.mem")) {
			result = new DuplicateUserIdService();
		} else if(command.equals("/member/registerMember.mem")) {
			result = new RegisterMemberService();
		} else if(command.equals("/member/dulpUserEmail.mem")) {
//			result = new DuplicateUserEmailService();
		} else if(command.equals("/member/sendAuthMail.mem")) {
			result = new SendAuthMailService();
		} else if(command.equals("/member/confirmCode.mem")) { 
			result = new CheckAuthCodeService();
		} else if(command.equals("/member/loginMember.mem")) {
			result = new LoginMemberService();
		} else if(command.equals("/member/logout.mem")) {
			result = new LogoutMemberService();
		} else if(command.equals("/member/getMemberInfoFromSes.mem")) {
			result = new ReadMemberInfoFromSes();
		} else if(command.equals("/member/myPage.mem")) {
			result = new MyPageService();
		}
		
		return result;
	}

	public MemberFactory(boolean redirect, String whereToGo) {
		super();
		this.redirect = redirect;
		this.whereToGo = whereToGo;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public String getWhereToGo() {
		return whereToGo;
	}

	public void setWhereToGo(String whereToGo) {
		this.whereToGo = whereToGo;
	}

	@Override
	public String toString() {
		return "MemberFactory [redirect=" + redirect + ", whereToGo=" + whereToGo + "]";
	}

	
}
