package com.miniproject.controller.member;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miniproject.service.member.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject 
	MemberService mService;
	
	@RequestMapping("login")
	public void login() {
		logger.info("login 호출됨.");
	}
	
	@RequestMapping("register")
	public void register() {
		logger.info("register 호출됨.");
	}
	
	@RequestMapping("sendAuthMail")
	public void sendAuthMail() {
		logger.info("sendAuthMail 호출됨.");
	}
	
	@RequestMapping("confirmCode")
	public void confirmCode() {
		logger.info("confirmCode 호출됨.");
	}
	
	@RequestMapping("dulpUserId")
	public void dulpUserId() {
		logger.info("dulpUserId 호출됨.");
	}
	
	

}
