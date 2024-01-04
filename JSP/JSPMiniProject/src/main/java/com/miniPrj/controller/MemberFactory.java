package com.miniPrj.controller;

import com.miniPrj.service.MemberService;
import com.miniPrj.service.member.CheckAuthCodeService;
import com.miniPrj.service.member.DuplicateUserEmailService;
import com.miniPrj.service.member.DuplicateUserIdService;
import com.miniPrj.service.member.RegisterMemberService;
import com.miniPrj.service.member.SendAuthMailService;

public class MemberFactory {
	private static MemberFactory instance = null;
	
	private MemberFactory() {}
	
	public static MemberFactory getInstance() {
		if(instance == null) instance = new MemberFactory();
		return instance;
	}
	
	// ���� ��ü�� ������ִ°��� �� Ŭ�������� �����.
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
		}
		return result;
	}
}
