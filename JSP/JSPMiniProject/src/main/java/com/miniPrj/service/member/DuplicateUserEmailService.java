package com.miniPrj.service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.controller.MemberFactory;
import com.miniPrj.service.MemberService;

public class DuplicateUserEmailService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		
		System.out.println("�̸��� �ߺ��˻��ؾ��Ѵ�.");
		
		return null;
	}

}
