package com.miniPrj.service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.controller.MemberFactory;
import com.miniPrj.service.MemberService;

public class RegisterMemberService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���������� ���� ������ �ϴ°�.
		
		System.out.println("��� ��� �ؾ��Ѵ�.");
		return null;
	}

}
