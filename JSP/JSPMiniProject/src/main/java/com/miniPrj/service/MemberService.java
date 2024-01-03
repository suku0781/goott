package com.miniPrj.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.controller.MemberFactory;

public interface MemberService {
	MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, MessagingException;
}
